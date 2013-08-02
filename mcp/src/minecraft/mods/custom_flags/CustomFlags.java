package mods.custom_flags;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mods.custom_flags.blocks.BlockFlagPole;
import net.minecraftforge.common.Configuration;

import static cpw.mods.fml.common.Mod.*;

/**
 * User: nerd-boy
 * Date: 2/08/13
 * Time: 11:14 AM
 *
 * The main entry class for the Custom Flags Mod
 */

@Mod(modid = "custom_flags", name="Custom Flags", version = "dev 1")
public class CustomFlags {

    @Instance("custom_flags")
    public static CustomFlags INSTANCE;

    public static int CAHCE_SIZE;

    public static BlockFlagPole blockFlagPole;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        //Load Config, register blocks & Items

        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        CAHCE_SIZE = config.get(Configuration.CATEGORY_GENERAL, "Cache Size", 25).getInt(25);

        blockFlagPole = new BlockFlagPole(config.getBlock("Flag Pole", 2700).getInt(2700));


        if(config.hasChanged()){
            config.save();
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event){

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        //Register render handelers
    }
}
