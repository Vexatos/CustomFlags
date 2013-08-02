package mods.custom_flags.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

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
        Tessellator tess = Tessellator.instance;
        float size = 1.5F;
        switch (type) {
            case ENTITY:
                GL11.glTranslatef(1F, 0, 1F);
                size = 1;
            case EQUIPPED:

                tess.startDrawingQuads();
                tess.addVertexWithUV(7F/16F, size, 0, icon.getInterpolatedU(0), icon.getInterpolatedV(16));
                tess.addVertexWithUV(9F/16F, size, 0, icon.getInterpolatedU(4), icon.getInterpolatedV(16));
                tess.addVertexWithUV(9F/16F, 0, 0, icon.getInterpolatedU(4), icon.getInterpolatedV(0));
                tess.addVertexWithUV(7F/16F, 0, 0, icon.getInterpolatedU(0), icon.getInterpolatedV(0));
                tess.draw();


                tess.startDrawingQuads();
                tess.addVertexWithUV(7F/16F, 0, 0F/16F, icon.getInterpolatedU(4), icon.getInterpolatedV(0));
                tess.addVertexWithUV(7F/16F, 0, 2F/16F, icon.getInterpolatedU(8), icon.getInterpolatedV(0));
                tess.addVertexWithUV(7F/16F, size, 2F/16F, icon.getInterpolatedU(8), icon.getInterpolatedV(16));
                tess.addVertexWithUV(7F/16F, size, 0F/16F, icon.getInterpolatedU(4), icon.getInterpolatedV(16));
                tess.draw();


                tess.startDrawingQuads();
                tess.addVertexWithUV(7F/16F, 0, 2F/16F, icon.getInterpolatedU(8), icon.getInterpolatedV(0));
                tess.addVertexWithUV(9F/16F, 0, 2F/16F, icon.getInterpolatedU(12), icon.getInterpolatedV(0));
                tess.addVertexWithUV(9F/16F, size, 2F/16F, icon.getInterpolatedU(12), icon.getInterpolatedV(16));
                tess.addVertexWithUV(7F/16F, size, 2F/16F, icon.getInterpolatedU(8), icon.getInterpolatedV(16));
                tess.draw();


                tess.startDrawingQuads();
                tess.addVertexWithUV(9F/16F, size, 0F/16F, icon.getInterpolatedU(12), icon.getInterpolatedV(16));

                tess.addVertexWithUV(9F/16F, size, 2F/16F, icon.getInterpolatedU(16), icon.getInterpolatedV(16));

                tess.addVertexWithUV(9F/16F, 0, 2F/16F, icon.getInterpolatedU(16), icon.getInterpolatedV(0));
                tess.addVertexWithUV(9F/16F, 0, 0F/16F, icon.getInterpolatedU(12), icon.getInterpolatedV(0));
                tess.draw();


                icon = Block.wood.getIcon(0,0);

                tess.startDrawingQuads();
                tess.addVertexWithUV(7F / 16F, 0, 0F / 16F, icon.getInterpolatedU(6), icon.getInterpolatedV(6));
                tess.addVertexWithUV(9F / 16F, 0, 0F / 16F, icon.getInterpolatedU(10), icon.getInterpolatedV(6));
                tess.addVertexWithUV(9F / 16F, 0, 2F / 16F, icon.getInterpolatedU(10), icon.getInterpolatedV(10));
                tess.addVertexWithUV(7F / 16F, 0, 2F / 16F, icon.getInterpolatedU(6), icon.getInterpolatedV(10));
                tess.draw();

                tess.startDrawingQuads();
                tess.addVertexWithUV(9F / 16F, size, 0F / 16F, icon.getInterpolatedU(6), icon.getInterpolatedV(6));
                tess.addVertexWithUV(7F / 16F, size, 0F / 16F, icon.getInterpolatedU(10), icon.getInterpolatedV(6));
                tess.addVertexWithUV(7F / 16F, size, 2F / 16F, icon.getInterpolatedU(10), icon.getInterpolatedV(10));
                tess.addVertexWithUV(9F / 16F, size, 2F / 16F, icon.getInterpolatedU(6), icon.getInterpolatedV(10));
                tess.draw();


                break;
            case EQUIPPED_FIRST_PERSON:

                tess.startDrawingQuads();
                tess.addVertexWithUV(7F/16F, 1, 0, icon.getInterpolatedU(0), icon.getInterpolatedV(16));
                tess.addVertexWithUV(9F/16F, 1, 0, icon.getInterpolatedU(4), icon.getInterpolatedV(16));
                tess.addVertexWithUV(9F/16F, 0, 0, icon.getInterpolatedU(4), icon.getInterpolatedV(0));
                tess.addVertexWithUV(7F/16F, 0, 0, icon.getInterpolatedU(0), icon.getInterpolatedV(0));
                tess.draw();


                tess.startDrawingQuads();
                tess.addVertexWithUV(7F/16F, 0, 0F/16F, icon.getInterpolatedU(4), icon.getInterpolatedV(0));
                tess.addVertexWithUV(7F/16F, 0, 2F/16F, icon.getInterpolatedU(8), icon.getInterpolatedV(0));
                tess.addVertexWithUV(7F/16F, 1, 2F/16F, icon.getInterpolatedU(8), icon.getInterpolatedV(16));
                tess.addVertexWithUV(7F/16F, 1, 0F/16F, icon.getInterpolatedU(4), icon.getInterpolatedV(16));
                tess.draw();



                break;
            case INVENTORY:



                tess.startDrawingQuads();
                tess.addVertexWithUV(6, 13, 0, icon.getInterpolatedU(0), icon.getInterpolatedV(16));
                tess.addVertexWithUV(8, 15, 0, icon.getInterpolatedU(4), icon.getInterpolatedV(16));
                tess.addVertexWithUV(8, 5, 0, icon.getInterpolatedU(4), icon.getInterpolatedV(0));
                tess.addVertexWithUV(6, 3, 0, icon.getInterpolatedU(0), icon.getInterpolatedV(0));
                tess.draw();


                tess.startDrawingQuads();
                tess.addVertexWithUV(10, 3, 0, icon.getInterpolatedU(8), icon.getInterpolatedV(0));
                tess.addVertexWithUV(8, 5, 0, icon.getInterpolatedU(4), icon.getInterpolatedV(0));
                tess.addVertexWithUV(8, 15, 0, icon.getInterpolatedU(4), icon.getInterpolatedV(16));
                tess.addVertexWithUV(10, 13, 0, icon.getInterpolatedU(8), icon.getInterpolatedV(16));
                tess.draw();


                icon = Block.wood.getIcon(0,0);

                tess.startDrawingQuads();
                tess.addVertexWithUV(8, 2, 0, icon.getInterpolatedU(6), icon.getInterpolatedV(10));
                tess.addVertexWithUV(6, 3, 0, icon.getInterpolatedU(10), icon.getInterpolatedV(10));
                tess.addVertexWithUV(8, 5, 0, icon.getInterpolatedU(10), icon.getInterpolatedV(6));
                tess.addVertexWithUV(10, 3, 0, icon.getInterpolatedU(6), icon.getInterpolatedV(6));
                tess.draw();


                break;
            case FIRST_PERSON_MAP:
                break;
        }
    }
}
