package mods.custom_flags.utils;

import cpw.mods.fml.common.network.IGuiHandler;
import mods.custom_flags.client.gui.GuiFlagDesigner;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Aaron on 3/08/13.
 */
public class CustomFlagsGuiHandeler implements IGuiHandler {

    public static final int FlagDesignID = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if(ID == FlagDesignID){
            return new GuiFlagDesigner(player);
        }

        return null;
    }
}
