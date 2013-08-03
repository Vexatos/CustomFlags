package mods.custom_flags.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

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
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {

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
}
