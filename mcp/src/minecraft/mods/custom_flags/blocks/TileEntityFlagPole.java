package mods.custom_flags.blocks;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
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

        if(par1NBTTagCompound.hasKey("flag")){
            ItemStack.loadItemStackFromNBT((NBTTagCompound) par1NBTTagCompound.getTag("flag"));
        }else{
            flag = null;
        }

    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);

        if(flag != null){
            NBTTagCompound flagCompound = new NBTTagCompound();
            flag.writeToNBT(flagCompound);
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        return super.getDescriptionPacket();
    }

    public boolean hasFlag(){
        return flag !=  null;
    }

    public void setFlag(ItemStack flag) {
        this.flag = flag;
    }

    public ItemStack getFlag() {
        return flag;
    }
}
