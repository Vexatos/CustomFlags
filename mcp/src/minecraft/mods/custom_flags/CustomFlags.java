package mods.custom_flags;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import mods.custom_flags.blocks.BlockFlagPole;
import mods.custom_flags.blocks.TileEntityFlagPole;
import mods.custom_flags.items.FlagRecipie;
import mods.custom_flags.items.ItemFlag;
import mods.custom_flags.packet.CustomFlagsPacketHandeler;
import mods.custom_flags.packet.FlagTileEntityDescripPacket;
import mods.custom_flags.packet.UpdateHeldFlagImagePacket;
import mods.custom_flags.utils.CustomFlagsGuiHandeler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;

import javax.swing.*;

import static cpw.mods.fml.common.registry.GameRegistry.*;

//import static cpw.mods.fml.common.Mod.*;

/**
 * User: nerd-boy
 * Date: 2/08/13
 * Time: 11:14 AM
 *
 * The main entry class for the Custom Flags Mod
 */

@Mod(modid = "custom_flags", name="Custom Flags", version = "dev 1")
@NetworkMod(packetHandler = CustomFlagsPacketHandeler.class, clientSideRequired = true,
        channels = {
        FlagTileEntityDescripPacket.channel, UpdateHeldFlagImagePacket.channel
})
public class CustomFlags {

    @Mod.Instance("custom_flags")
    public static CustomFlags INSTANCE;

    @SidedProxy(clientSide = "mods.custom_flags.client.ClientProxy", serverSide = "mods.custom_flags.CommonProxy")
    public static CommonProxy PROXY;

    public static int CAHCE_SIZE;
    public static int period;
    public static int flag_sections;

    public static BlockFlagPole blockFlagPole;
    public static ItemFlag itemFlag;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        //Load Config, register blocks & Items

        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        CAHCE_SIZE = config.get(Configuration.CATEGORY_GENERAL, "Cache Size", 25).getInt(25);

        blockFlagPole = new BlockFlagPole(config.getBlock("Flag Pole Id", 2700).getInt(2700));
        itemFlag = new ItemFlag(config.getItem("Flag Id", 24532).getInt());

        registerBlock(blockFlagPole, "flagpole");
        registerTileEntity(TileEntityFlagPole.class, "flagpole_tile");

        addRecipe(new ItemStack(blockFlagPole), new Object[]{
                "S",
                "S",
                "S",
                Character.valueOf('S'), Item.stick});

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

        flag_sections = config.get(Configuration.CATEGORY_GENERAL, "Animation Detail Level", 16).getInt(16);
        flag_sections = Math.max(0, flag_sections);


        addRecipe(new FlagRecipie());

        if(config.hasChanged()){
            config.save();
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){

        PROXY.registerRenderers();

        NetworkRegistry.instance().registerGuiHandler(this, new CustomFlagsGuiHandeler());

    }
}
