package mods.custom_flags.blocks;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);

        //ItemStack.loadItemStackFromNBT(par1NBTTagCompound);

    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
    }

    public boolean hasFlag(){
        return flag !=  null;
    }

}
