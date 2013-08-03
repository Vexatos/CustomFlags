package mods.custom_flags.client.renderer;

import mods.custom_flags.client.utils.ImageCahce;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Created by Aaron on 2/08/13.
 */
public class FlagItemRenderer implements IItemRenderer {
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return type != ItemRenderType.EQUIPPED_FIRST_PERSON;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return helper == ItemRendererHelper.ENTITY_BOBBING || helper == ItemRendererHelper.ENTITY_ROTATION;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        Tessellator tess = Tessellator.instance;
        ImageCahce.setTexture(item);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);


        switch (type) {
            case ENTITY:

                GL11.glTranslatef(-0.5F, -0.25F, 0);

                tess.startDrawingQuads();
                tess.addVertexWithUV(1F/16F, 15F/16F, 0, 1, 0);
                tess.addVertexWithUV(15F/16F, 15F/16F, 0, 0, 0);
                tess.addVertexWithUV(15F/16F, 1F/16F, 0, 0, 1);
                tess.addVertexWithUV(1F/16F, 1F/16F, 0, 1, 1);
                tess.draw();

                tess.startDrawingQuads();
                tess.addVertexWithUV(1F/16F, 1F/16F, 0, 1, 1);
                tess.addVertexWithUV(15F/16F, 1F/16F, 0, 0, 1);
                tess.addVertexWithUV(15F/16F, 15F/16F, 0, 0, 0);
                tess.addVertexWithUV(1F/16F, 15F/16F, 0, 1, 0);
                tess.draw();

                break;
            case EQUIPPED:

                tess.startDrawingQuads();
                tess.addVertexWithUV(1F/16F, 15F/16F, 0, 1, 0);
                tess.addVertexWithUV(15F/16F, 15F/16F, 0, 0, 0);
                tess.addVertexWithUV(15F/16F, 1F/16F, 0, 0, 1);
                tess.addVertexWithUV(1F/16F, 1F/16F, 0, 1, 1);
                tess.draw();

                break;
            case EQUIPPED_FIRST_PERSON:

                tess.startDrawingQuads();
                tess.addVertexWithUV(0, 1, 0, 1, 0);
                tess.addVertexWithUV(1, 1, 0, 0, 0);
                tess.addVertexWithUV(1, 0, 0, 0, 1);
                tess.addVertexWithUV(0, 0, 0, 1, 1);
                tess.draw();


                break;
            case INVENTORY:

                tess.startDrawingQuads();
                tess.addVertexWithUV(1, 15, 0, 0, 1);
                tess.addVertexWithUV(15, 15, 0, 1, 1);
                tess.addVertexWithUV(15, 1, 0, 1, 0);
                tess.addVertexWithUV(1, 1, 0, 0, 0);
                tess.draw();

                break;
            case FIRST_PERSON_MAP:


                tess.startDrawingQuads();
                tess.addVertexWithUV(-8,128+8,-0.009999999776482582D,0,1);
                tess.addVertexWithUV(128+8,128+8,-0.009999999776482582D,1,1);
                tess.addVertexWithUV(128+8,-8,-0.009999999776482582D,1,0);
                tess.addVertexWithUV(-8,-8,-0.009999999776482582D,0,0);
                tess.draw();

                break;
        }

        GL11.glDisable(GL11.GL_BLEND);

    }
}
