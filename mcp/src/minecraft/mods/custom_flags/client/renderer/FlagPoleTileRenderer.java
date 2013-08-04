package mods.custom_flags.client.renderer;

import mods.custom_flags.CustomFlags;
import mods.custom_flags.blocks.TileEntityFlagPole;
import mods.custom_flags.client.utils.ImageCahce;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import net.minecraft.util.Icon;
import net.minecraftforge.event.ListenerList;
import org.lwjgl.opengl.GL11;

import java.util.List;

/**
 * User: nerd-boy
 * Date: 2/08/13
 * Time: 2:33 PM
 * TODO: Add discription
 */
public class FlagPoleTileRenderer extends TileEntitySpecialRenderer {


    public static final int sections = 32;

    public static double getZLevel(float x, float size){
        return Math.pow(x, 0.5/(size/5)) * Math.sin(Math.PI * ( -x/size * 3 + ((float)(System.currentTimeMillis()% CustomFlags.period)) / (0.5F*(float)CustomFlags.period))) / 4;
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {


        if(tileentity instanceof TileEntityFlagPole){
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


            tess.startDrawingQuads();
            tess.addVertexWithUV(7F / 16F, 0, 7F / 16F, icon.getInterpolatedU(12), icon.getInterpolatedV(0));
            tess.addVertexWithUV(7F / 16F, 0, 9F / 16F, icon.getInterpolatedU(16), icon.getInterpolatedV(0));
            tess.addVertexWithUV(7F / 16F, 1, 9F / 16F, icon.getInterpolatedU(16), icon.getInterpolatedV(16));
            tess.addVertexWithUV(7F / 16F, 1, 7F / 16F, icon.getInterpolatedU(12), icon.getInterpolatedV(16));
            tess.draw();

            icon = Block.wood.getIcon(0,0);

            tess.startDrawingQuads();
            tess.addVertexWithUV(7F / 16F, 0, 7F / 16F, icon.getInterpolatedU(6), icon.getInterpolatedV(6));
            tess.addVertexWithUV(9F / 16F, 0, 7F / 16F, icon.getInterpolatedU(10), icon.getInterpolatedV(6));
            tess.addVertexWithUV(9F / 16F, 0, 9F / 16F, icon.getInterpolatedU(10), icon.getInterpolatedV(10));
            tess.addVertexWithUV(7F / 16F, 0, 9F / 16F, icon.getInterpolatedU(6), icon.getInterpolatedV(10));
            tess.draw();

            tess.startDrawingQuads();
            tess.addVertexWithUV(9F / 16F, 1, 7F / 16F, icon.getInterpolatedU(6), icon.getInterpolatedV(6));
            tess.addVertexWithUV(7F / 16F, 1, 7F / 16F, icon.getInterpolatedU(10), icon.getInterpolatedV(6));
            tess.addVertexWithUV(7F / 16F, 1, 9F / 16F, icon.getInterpolatedU(10), icon.getInterpolatedV(10));
            tess.addVertexWithUV(9F / 16F, 1, 9F / 16F, icon.getInterpolatedU(6), icon.getInterpolatedV(10));
            tess.draw();



            if(((TileEntityFlagPole) tileentity).hasFlag())
            {
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

                List<ItemStack> flags = ((TileEntityFlagPole) tileentity).getFlags();


                GL11.glDisable(GL11.GL_LIGHTING);
                for(int flagIndex = 0; flagIndex < flags.size(); flagIndex++){
                    ItemStack flag = flags.get(flagIndex);
                    ImageCahce.setTexture(flag);



                    for(int i = 0; i < 8; i++){
                        tess.startDrawingQuads();

                        double z1 = getZLevel((float)(i) / 8F + flagIndex, 3);
                        double z2 = getZLevel((float)(i+1) / 8F + flagIndex, 3);

                        tess.addVertexWithUV(7F / 16F-(float)(i) / 8F - flagIndex, 0, 8F / 16F+z1,   (float)(i) / 8F, 0.999);
                        tess.addVertexWithUV(7F /16F- (float)(i+1) / 8F- flagIndex, 0, 8F / 16F+z2, (float)(i+1) / 8F, 0.999);
                        tess.addVertexWithUV(7F /16F-(float)(i+1) / 8F- flagIndex, 1, 8F / 16F+z2,    (float)(i+1) / 8F, 0.001);
                        tess.addVertexWithUV(7F / 16F- (float)(i) / 8F- flagIndex, 1, 8F / 16F+z1,  (float)(i) / 8F, 0.001);

                        tess.addVertexWithUV(7F / 16F- (float)(i) / 8F - flagIndex, 1, 8F / 16F+z1,  (float)(i) / 8F, 0.001);
                        tess.addVertexWithUV(7F /16F-(float)(i+1) / 8F - flagIndex, 1, 8F / 16F+z2,    (float)(i+1) / 8F, 0.001);
                        tess.addVertexWithUV(7F /16F- (float)(i+1) / 8F - flagIndex, 0, 8F / 16F+z2, (float)(i+1) / 8F, 0.999);
                        tess.addVertexWithUV(7F / 16F-(float)(i) / 8F - flagIndex, 0, 8F / 16F+z1,   (float)(i) / 8F, 0.999);

                        tess.draw();
                    }
                }
                GL11.glEnable(GL11.GL_LIGHTING);



                GL11.glDisable(GL11.GL_BLEND);

            }



            GL11.glPopMatrix();

        }
    }
}
