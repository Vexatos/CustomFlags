package mods.custom_flags.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * User: nerd-boy
 * Date: 2/08/13
 * Time: 11:53 AM
 * TODO: Add discription
 */
public class BlockFlag extends BlockContainer{

    public BlockFlag(int id) {
        super(id, Material.wood);
    }


    @Override
    public TileEntity createNewTileEntity(World world) {
        return null;
    }
}
