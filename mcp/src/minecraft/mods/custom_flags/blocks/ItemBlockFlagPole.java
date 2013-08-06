package mods.custom_flags.blocks;

import mods.custom_flags.CustomFlags;
import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTextureTile;

/**
 * User: nerd-boy
 * Date: 6/08/13
 * Time: 12:40 PM
 * TODO: Add discription
 */
public class ItemBlockFlagPole extends ItemMultiTextureTile{

    public ItemBlockFlagPole(int id) {
        super(id, CustomFlags.blockFlagPole, new String[] {"oak", "spruce", "birch", "jungle", "iron"});
        this.setUnlocalizedName("custom_flags:flagpole");
    }
}
