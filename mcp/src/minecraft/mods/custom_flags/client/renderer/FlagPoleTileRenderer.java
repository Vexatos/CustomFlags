package mods.custom_flags.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import net.minecraft.util.Icon;
import org.lwjgl.opengl.GL11;

/**
 * User: nerd-boy
 * Date: 2/08/13
 * Time: 2:33 PM
 * TODO: Add discription
 */
public class FlagPoleTileRenderer extends TileEntitySpecialRenderer {
    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {

        Minecraft.getMinecraft().renderEngine.func_110577_a(TextureMap.field_110575_b);
        Icon icon = Block.wood.getIcon(2,0);

        GL11.glPushMatrix();

        GL11.glTranslated(d0, d1, d2);

        GL11.glColor3f(1,1,1);

        Tessellator tess = Tessellator.instance;


        tess.startDrawingQuads();
        tess.addVertexWithUV(7F / 16F, 0, 9F / 16F, icon.getInterpolatedU(0), icon.getInterpolatedV(0));
        tess.addVertexWithUV(9F / 16F, 0, 9F / 16F, icon.getInterpolatedU(4), icon.getInterpolatedV(0));
        tess.addVertexWithUV(9F / 16F, 1, 9F / 16F, icon.getInterpolatedU(4), icon.getInterpolatedV(16));
        tess.addVertexWithUV(7F / 16F, 1, 9F / 16F, icon.getInterpolatedU(0), icon.getInterpolatedV(16));
        tess.draw();

        tess.startDrawingQuads();
        tess.addVertexWithUV(9F / 16F, 0, 9F / 16F, icon.getInterpolatedU(4), icon.getInterpolatedV(0));
        tess.addVertexWithUV(9F / 16F, 0, 7F / 16F, icon.getInterpolatedU(8), icon.getInterpolatedV(0));
        tess.addVertexWithUV(9F / 16F, 1, 7F / 16F, icon.getInterpolatedU(8), icon.getInterpolatedV(16));
        tess.addVertexWithUV(9F / 16F, 1, 9F / 16F, icon.getInterpolatedU(4), icon.getInterpolatedV(16));
        tess.draw();


        tess.startDrawingQuads();
        tess.addVertexWithUV(9F / 16F, 0, 7F / 16F, icon.getInterpolatedU(8), icon.getInterpolatedV(0));
        tess.addVertexWithUV(7F / 16F, 0, 7F / 16F, icon.getInterpolatedU(12), icon.getInterpolatedV(0));
        tess.addVertexWithUV(7F / 16F, 1, 7F / 16F, icon.getInterpolatedU(12), icon.getInterpolatedV(16));
        tess.addVertexWithUV(9F / 16F, 1, 7F / 16F, icon.getInterpolatedU(8), icon.getInterpolatedV(16));
        tess.draw();

        /*
        tess.startDrawingQuads();
        tess.addVertex(7F / 16F, 0, 9F / 16F);
        tess.addVertex(9F / 16F, 0, 9F / 16F);
        tess.addVertex(9F / 16F, 1, 9F / 16F);
        tess.addVertex(7F / 16F, 1, 9F / 16F);
        tess.draw();
        */

        GL11.glPopMatrix();



    }
}
