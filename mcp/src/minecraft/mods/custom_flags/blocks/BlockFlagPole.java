package mods.custom_flags.blocks;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import mods.custom_flags.items.ItemFlag;
import mods.custom_flags.packet.FlagTileEntityDescripPacket;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import java.util.List;

/**
 * User: nerd-boy
 * Date: 2/08/13
 * Time: 11:53 AM
 *
 * Block class for a flag pole
 */
public class BlockFlagPole extends BlockContainer{

    public BlockFlagPole(int id) {
        super(id, Material.wood);
        this.setCreativeTab(CreativeTabs.tabDecorations);

        this.setUnlocalizedName("custom_flags:flagpole");

        setBlockBounds(6F/16F, 0, 6F/16F, 10F/16F, 1,  10F/16F);
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {

        TileEntity te = world.getBlockTileEntity(x,y,z);
        ItemStack stack = par5EntityPlayer.getCurrentEquippedItem();
        if(te != null && te instanceof TileEntityFlagPole){
            if(stack == null){

                if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER){
                    if(((TileEntityFlagPole) te).hasFlag()){
                        par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, ((TileEntityFlagPole) te).popFlag());
                        PacketDispatcher.sendPacketToAllPlayers(te.getDescriptionPacket());
                    }
                }

                return true;
            }else if(stack.getItem() instanceof ItemFlag){



                if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER){
                    if(((TileEntityFlagPole) te).setFlag(stack) && !par5EntityPlayer.capabilities.isCreativeMode){
                        par5EntityPlayer.inventory.decrStackSize(par5EntityPlayer.inventory.currentItem, 1);
                    }
                    PacketDispatcher.sendPacketToAllPlayers(te.getDescriptionPacket());

                }

                return true;
            }
        }



        return super.onBlockActivated(world, x, y, z, par5EntityPlayer, par6, par7, par8, par9);
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityFlagPole();
    }

    @Override
    public Icon getIcon(int par1, int par2) {
        return Block.wood.getIcon(2,0);
    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {

        if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER){
            TileEntity te = par1World.getBlockTileEntity(par2, par3, par4);
            if(te != null && te instanceof TileEntityFlagPole){
                List<ItemStack> flags = ((TileEntityFlagPole)te).getFlags();

                for(ItemStack f : flags){
                    par1World.spawnEntityInWorld(new EntityItem(par1World, par2, par3, par4, f));
                }
            }
        }
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }


}
