package mods.custom_flags;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import mods.custom_flags.blocks.BlockFlagPole;
import mods.custom_flags.blocks.ItemBlockFlagPole;
import mods.custom_flags.blocks.TileEntityFlagPole;
import mods.custom_flags.items.FlagRecipie;
import mods.custom_flags.items.ItemFlag;
import mods.custom_flags.packet.CustomFlagsPacketHandeler;
import mods.custom_flags.packet.FlagTileEntityDescripPacket;
import mods.custom_flags.packet.UpdateHeldFlagImagePacket;
import mods.custom_flags.utils.CustomFlagsGuiHandeler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMultiTextureTile;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;

import javax.swing.*;

import java.util.Locale;

import static cpw.mods.fml.common.registry.GameRegistry.*;

//import static cpw.mods.fml.common.Mod.*;

/**
 * User: nerd-boy
 * Date: 2/08/13
 * Time: 11:14 AM
 *
 * The main entry class for the Custom Flags Mod
 */

@Mod(modid = "custom_flags", name="Custom Flags", version = "1.3.1")
@NetworkMod(packetHandler = CustomFlagsPacketHandeler.class, clientSideRequired = true,
        channels = {
        FlagTileEntityDescripPacket.channel, UpdateHeldFlagImagePacket.channel
})
public class CustomFlags {

    @Mod.Instance("custom_flags")
    public static CustomFlags INSTANCE;

    @SidedProxy(clientSide = "mods.custom_flags.client.ClientProxy", serverSide = "mods.custom_flags.CommonProxy")
    public static CommonProxy PROXY;

    public static boolean FcLoadImages;

    public static int CAHCE_SIZE;
    public static int period;
    public static int flag_sections;

    public static int BUFFER_SIZE = 10;

    public static BlockFlagPole blockFlagPole;
    public static ItemFlag itemFlag;

    public static boolean DISPLAY_INSTRUCTIONS;

    public static Configuration config;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        //Load Config, register blocks & Items

        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        CAHCE_SIZE = config.get(Configuration.CATEGORY_GENERAL, "Cache Size", 25).getInt(25);

        blockFlagPole = new BlockFlagPole(config.getBlock("Flag Pole Id", 2700).getInt(2700));
        itemFlag = new ItemFlag(config.getItem("Flag Id", 24532).getInt());

        registerBlock(blockFlagPole, "flagpole");
        registerTileEntity(TileEntityFlagPole.class, "flagpole_tile");
        Item.itemsList[blockFlagPole.blockID] = new ItemBlockFlagPole(blockFlagPole.blockID - 256);

        for(int i = 0; i < 4; i++){
            GameRegistry.addRecipe(new ItemStack(Item.itemsList[blockFlagPole.blockID], 4, i),
                    new Object[]{
                            "W",
                            "W",
                            "W", Character.valueOf('W'), new ItemStack(Block.wood, 1, i)
                    });
        }
        GameRegistry.addRecipe(new ItemStack(Item.itemsList[blockFlagPole.blockID], 4, 4),
                new Object[]{
                        "I",
                        "I",
                        "I", Character.valueOf('I'), Item.ingotIron
                });


        period = ((10-config.get(Configuration.CATEGORY_GENERAL, "Flag Speed", 3).getInt(3)) * 100) + 250;
        period = Math.max(period, 250);
        period = Math.min(period, 1250);


        if(config.get(Configuration.CATEGORY_GENERAL, "Use System L&F", true).getBoolean(true)){
            try {
                // Set System L&F
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());
            }
            catch (Exception e) {e.printStackTrace();}
        }

        FcLoadImages = config.get(Configuration.CATEGORY_GENERAL, "Load Images in FileChooser", true).getBoolean(true);

        flag_sections = config.get(Configuration.CATEGORY_GENERAL, "Animation Detail Level", 16).getInt(16);
        flag_sections = Math.max(0, flag_sections);

        DISPLAY_INSTRUCTIONS = config.get(Configuration.CATEGORY_GENERAL, "Display Instruction Message", true).getBoolean(true);

        addRecipe(new FlagRecipie());

        if(config.hasChanged()){
            config.save();
        }
    }

    @Mod.EventHandler
    public void init(FMLServerStartingEvent event){
        event.registerServerCommand(new GiveInstructionsCommand());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){

        PROXY.registerRenderers();

        NetworkRegistry.instance().registerGuiHandler(this, new CustomFlagsGuiHandeler());

    }
}
