package mods.custom_flags;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

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

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        //Load Config, register blocks & Items
    }

    @EventHandler
    public void init(FMLInitializationEvent event){

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        //Register render handelers
    }
}
