package mods.custom_flags.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.custom_flags.utils.ImageData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * User: nerd-boy
 * Date: 2/08/13
 * Time: 11:52 AM
 * TODO: Add discription
 */
public class ItemFlag extends Item {


    public ItemFlag(int id) {
        super(id);

        this.setMaxStackSize(1);
    }

    @SideOnly(Side.CLIENT)
    public byte[] getImageData(ItemStack stack) {

        if(stack.hasTagCompound() && stack.getTagCompound().hasKey("imgKey") && stack.getTagCompound().hasKey("img")){
            return stack.getTagCompound().getByteArray("img");
        }else{

            if(!stack.hasTagCompound()){
                stack.setTagCompound(new NBTTagCompound());
            }

            stack.getTagCompound().setByteArray("img", ImageData.defaultData);
            return ImageData.defaultData;
        }

    }

    public void setImageData(ItemStack stack, byte[] image, World world) {

        if(!stack.hasTagCompound()){
            stack.setTagCompound(new NBTTagCompound());
        }

        stack.getTagCompound().setInteger("imgKey", world.getUniqueDataId("flag"));
        stack.getTagCompound().setByteArray("img", image);

    }

    public boolean hasImageData(ItemStack stack){
        return stack.hasTagCompound() &&
                stack.getTagCompound().hasKey("imgKey") &&
                stack.getTagCompound().hasKey("img");
    }


    public String getKey(ItemStack stack) {
        if(hasImageData(stack)){
            return Integer.toString(stack.getTagCompound().getInteger("imgKey"));
        }else{
            return "default";
        }
    }
}
