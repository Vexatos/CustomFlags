package mods.custom_flags.items;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.custom_flags.CustomFlags;
import mods.custom_flags.packet.UpdateHeldFlagImagePacket;
import mods.custom_flags.utils.ImageData;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.common.MinecraftForge;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * User: nerd-boy
 * Date: 2/08/13
 * Time: 11:52 AM
 * TODO: Add discription
 */
public class ItemFlag extends Item {


    public ItemFlag(int id) {
        super(id);

        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setUnlocalizedName("custom_flags:flag");
        this.setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer player) {

        if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT){

            player.openGui(CustomFlags.INSTANCE, 0, world, (int)player.posX, (int)player.posY, (int)player.posZ);

            /*
            JFileChooser fc = new JFileChooser();

            if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                try{
                    BufferedImage image = ImageIO.read(fc.getSelectedFile());
                    this.setImageData(par1ItemStack, new ImageData(image, 32, 32).getByteArray(), par3EntityPlayer.worldObj);

                    PacketDispatcher.sendPacketToServer(UpdateHeldFlagImagePacket.generatePacket(par3EntityPlayer.username, par1ItemStack));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            */
        }


        return super.onItemRightClick(par1ItemStack, world, player);
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









    public MapData getMapData(ItemStack par1ItemStack, World par2World)
    {return null;}

    public void updateMapData(World par1World, Entity par2Entity, MapData par3MapData)
    {}
    /**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {}

    /**
     * returns null if no update is to be sent
     */
    public Packet createMapDataPacket(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {return null;}

    /**
     * Called when item is crafted/smelted. Used only by maps so far.
     */
    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {}

    @SideOnly(Side.CLIENT)

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {}


}
