package mods.custom_flags.blocks;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import mods.custom_flags.items.ItemFlag;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.ASMifier;

import java.util.List;

/**
 * User: nerd-boy
 * Date: 2/08/13
 * Time: 11:53 AM
 *
 * Block class for a flag pole
 */
public class BlockFlagPole extends BlockContainer{

    private static final float[] woodTexDims = new float[5];
    private static final float[] ironTexDims = new float[5];
    private static final AxisAlignedBB[] bounds = new AxisAlignedBB[3];
    static{
        woodTexDims[0] = 0F;
        woodTexDims[1] = 4F;
        woodTexDims[2] = 8F;
        woodTexDims[3] = 12F;
        woodTexDims[4] = 16F;

        ironTexDims[0] = 1F;
        ironTexDims[1] = 4.5F;
        ironTexDims[2] = 8F;
        ironTexDims[3] = 11.5F;
        ironTexDims[4] = 15;
    }

    public BlockFlagPole(int id) {
        super(id, Material.wood);
        this.setCreativeTab(CreativeTabs.tabDecorations);

        this.setUnlocalizedName("custom_flags:flagpole");
    }

    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        for(int i = 0; i < 5; i++){
            par3List.add(new ItemStack(par1, 1, i));
        }

    }

    public int damageDropped(int par1)
    {
        return par1 % 5;
    }


    @Override
    public void registerIcons(IconRegister par1IconRegister) {
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        int side = getOrient(par1World.getBlockMetadata(par2, par3, par4));
        switch(side){
            case 0:
                return AxisAlignedBB.getAABBPool().getAABB((double)par2 + 6F/16F, (double)par3 + 0, (double)par4 + 6F/16F, (double)par2 + 10F/16F, (double)par3 + 1, (double)par4 + 10F/16F);
            case 1:
                return AxisAlignedBB.getAABBPool().getAABB((double)par2 + 6F/16F, (double)par3 + 13F/16F, (double)par4 + 0, (double)par2 + 10F/16F, (double)par3 + 1, (double)par4 + 1);
            case 2:
                return AxisAlignedBB.getAABBPool().getAABB((double)par2 + 0, (double)par3 + 13F/16F, (double)par4 + 6F/16F, (double)par2 + 1, (double)par3 + 1, (double)par4 + 10F/16F);
        }
        return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
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

    public float getTextDim(int metadata, int section){
        if(metadata % 5 == 4){
            return ironTexDims[section];
        }else{
            return woodTexDims[section];
        }
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

        if(par2 % 5 == 4)
            return Block.blockIron.getIcon(par1,0);
        else{
            return Block.wood.getIcon(par1,par2 % 5);
        }
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
        meta = meta % 5 + (5 * (side / 2));
        return meta;
    }

    public int getOrient(int meta){
        return meta / 5;
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
