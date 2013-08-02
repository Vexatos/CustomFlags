package mods.custom_flags.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;

/**
 * Created by Aaron on 2/08/13.
 */
public class FlagPoleItemRenderer implements IItemRenderer {

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return type != ItemRenderType.FIRST_PERSON_MAP;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return helper == ItemRendererHelper.ENTITY_BOBBING || helper == ItemRendererHelper.ENTITY_ROTATION;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        Icon icon = Block.wood.getIcon(2,0);

        switch (type) {
            case ENTITY:
                break;
            case EQUIPPED:
                break;
            case EQUIPPED_FIRST_PERSON:
                break;
            case INVENTORY:

                Tessellator tess = Tessellator.instance;

                tess.startDrawingQuads();
                tess.addVertexWithUV(7, 0, 0, icon.getInterpolatedU(0), icon.getInterpolatedV(0));
                tess.addVertexWithUV(8, 1, 0, icon.getInterpolatedU(4), icon.getInterpolatedV(0));
                tess.addVertexWithUV(8, 12, 0, icon.getInterpolatedU(4), icon.getInterpolatedV(16));
                tess.addVertexWithUV(7, 11, 0, icon.getInterpolatedU(0), icon.getInterpolatedV(16));
                tess.draw();

                tess.startDrawingQuads();
                tess.addVertexWithUV(9, 0, 0, icon.getInterpolatedU(0), icon.getInterpolatedV(0));
                tess.addVertexWithUV(8, 1, 0, icon.getInterpolatedU(4), icon.getInterpolatedV(0));
                tess.addVertexWithUV(8, 12, 0, icon.getInterpolatedU(4), icon.getInterpolatedV(16));
                tess.addVertexWithUV(9, 11, 0, icon.getInterpolatedU(0), icon.getInterpolatedV(16));
                tess.draw();

                break;
            case FIRST_PERSON_MAP:
                break;
        }
    }
}
