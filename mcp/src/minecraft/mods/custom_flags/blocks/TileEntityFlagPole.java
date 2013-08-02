package mods.custom_flags.blocks;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * User: nerd-boy
 * Date: 2/08/13
 * Time: 2:33 PM
 */
public class TileEntityFlagPole extends TileEntity {

    private ItemStack flag;

    public TileEntityFlagPole(){
    }


    public boolean shouldDrawFlag(){
        return flag !=  null;
    }

}
