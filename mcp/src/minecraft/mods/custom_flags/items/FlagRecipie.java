package mods.custom_flags.items;

import mods.custom_flags.CustomFlags;
import mods.custom_flags.utils.ImageData;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * Created by Aaron on 2/08/13.
 */
public class FlagRecipie extends ShapedRecipes {

    private World worldTemp;

    public FlagRecipie() {
        super(2, 2, new ItemStack[]{
                new ItemStack(Block.cloth), new ItemStack(Block.cloth), new ItemStack(Block.cloth), new ItemStack(Block.cloth)
        }, new ItemStack(CustomFlags.itemFlag));
    }

    @Override
    public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World) {
        worldTemp = par2World;
        for (int i = 0; i <= 3 - this.recipeWidth; ++i)
        {
            for (int j = 0; j <= 3 - this.recipeHeight; ++j)
            {
                if (this.checkMatch(par1InventoryCrafting, i, j, true))
                {
                    return true;
                }

                if (this.checkMatch(par1InventoryCrafting, i, j, false))
                {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks if the region of a crafting inventory is match for the recipe.
     */
    private boolean checkMatch(InventoryCrafting par1InventoryCrafting, int par2, int par3, boolean par4)
    {
        for (int k = 0; k < 3; ++k)
        {
            for (int l = 0; l < 3; ++l)
            {
                int i1 = k - par2;
                int j1 = l - par3;
                ItemStack itemstack = null;

                if (i1 >= 0 && j1 >= 0 && i1 < this.recipeWidth && j1 < this.recipeHeight)
                {
                    if (par4)
                    {
                        itemstack = this.recipeItems[this.recipeWidth - i1 - 1 + j1 * this.recipeWidth];
                    }
                    else
                    {
                        itemstack = this.recipeItems[i1 + j1 * this.recipeWidth];
                    }
                }

                ItemStack itemstack1 = par1InventoryCrafting.getStackInRowAndColumn(k, l);

                if (itemstack1 != null || itemstack != null)
                {
                    if (itemstack1 == null && itemstack != null || itemstack1 != null && itemstack == null)
                    {
                        return false;
                    }

                    if (itemstack.itemID != itemstack1.itemID)
                    {
                        return false;
                    }


                }
            }
        }

        return true;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting) {
        ItemStack flagStack =  super.getCraftingResult(par1InventoryCrafting);

        ArrayList<Integer> cols = new ArrayList<Integer>(4);

        for(int i = 0; i < par1InventoryCrafting.getSizeInventory(); i++){
            ItemStack stack = par1InventoryCrafting.getStackInSlot(i);
            if(stack != null && stack.getItem().itemID == Block.cloth.blockID){
                cols.add(ItemDye.dyeColors[15 - stack.getItemDamage()] | 0xFF000000);
            }


        }

        ((ItemFlag)flagStack.getItem()).setImageData(flagStack, new ImageData(cols.get(0), cols.get(1), cols.get(2), cols.get(3)).getByteArray() , worldTemp);


        return flagStack;
    }
}
