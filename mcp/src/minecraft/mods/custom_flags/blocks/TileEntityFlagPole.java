package mods.custom_flags.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.custom_flags.CustomFlags;
import mods.custom_flags.packet.FlagTileEntityDescripPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import java.util.ArrayList;

/**
 * User: nerd-boy
 * Date: 2/08/13
 * Time: 2:33 PM
 */
public class TileEntityFlagPole extends TileEntity {


    private ArrayList<ItemStack> flags;

    private static final int MAX_FLAGS = 4;

    public TileEntityFlagPole(){
        flags = new ArrayList<ItemStack>(MAX_FLAGS);
    }


    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        int side = CustomFlags.blockFlagPole.getOrient(getBlockMetadata());
        switch (side){
            case 0:
                return  AxisAlignedBB.getAABBPool().getAABB(
                        xCoord - flags.size(),
                        yCoord,
                        zCoord,
                        xCoord + flags.size()+1,
                        yCoord + 1, zCoord + 1);
            case 1:
            case 2:
                return  AxisAlignedBB.getAABBPool().getAABB(
                        xCoord,
                        yCoord - flags.size(),
                        zCoord,
                        xCoord+1,
                        yCoord+ flags.size()+1, zCoord + 1);
        }

        return  AxisAlignedBB.getAABBPool().getAABB(
                xCoord - flags.size(),
                yCoord,
                zCoord,
                xCoord + flags.size()+1,
                yCoord + 1, zCoord + 1);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);

        flags = new ArrayList<ItemStack>(MAX_FLAGS);

        for(int i = 0; i < MAX_FLAGS; i++){
            if(par1NBTTagCompound.hasKey("flag"+i)){
                flags.add(ItemStack.loadItemStackFromNBT(par1NBTTagCompound.getCompoundTag("flag"+i)));
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);

        for(int i = 0; i < flags.size(); i++){
            NBTTagCompound flagCompound = new NBTTagCompound();
            flags.get(i).writeToNBT(flagCompound);
            par1NBTTagCompound.setCompoundTag("flag"+i, flagCompound);
        }
    }



    @Override
    public Packet getDescriptionPacket() {
        return FlagTileEntityDescripPacket.generatePacket(xCoord, yCoord, zCoord, flags);
    }

    public boolean hasFlag(){
        return flags.size() != 0;
    }

    public void clearFlags() {
        flags.clear();
    }

    public boolean setFlag(ItemStack flag) {
        if(flags.size() == MAX_FLAGS){
            return false;
        }else{
            this.flags.add(flag);
            return true;
        }
    }

    public ArrayList<ItemStack> getFlags() {
        return flags;
    }

    public boolean shouldRemoveFlag(EntityPlayer player){
        return (flags.size() <= MAX_FLAGS & !player.capabilities.isCreativeMode);
    }

    public ItemStack popFlag(){
        ItemStack flag = flags.get(flags.size()-1);
        flags.remove(flags.size()-1);
        return flag;
    }
}
