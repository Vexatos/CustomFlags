package mods.custom_flags.client.renderer;

import mods.custom_flags.CustomFlags;
import mods.custom_flags.client.utils.ImageCahce;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Created by Aaron on 2/08/13.
 */
public class FlagItemRenderer implements IItemRenderer {

    private static final ResourceLocation map_background = new ResourceLocation("textures/map/map_background.png");

    public static double getZLevel(float x, float size, long time){
        return Math.sin(Math.PI * ( -x/size * 3 + ((float)(time% CustomFlags.period)) / (0.5F*(float)CustomFlags.period))) / 4;
    }

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


        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        switch (type) {
            case ENTITY:

                ImageCahce.setTexture(item);

                GL11.glTranslatef(-0.5F, -0.25F, 0);

                if(item.isOnItemFrame()){
                    tess.startDrawingQuads();
                    tess.addVertexWithUV(0, 1, 0, 1, 0);
                    tess.addVertexWithUV(1, 1, 0, 0, 0);
                    tess.addVertexWithUV(1, 0, 0, 0, 1);
                    tess.addVertexWithUV(0, 0, 0, 1, 1);
                    tess.draw();

                    tess.startDrawingQuads();
                    tess.addVertexWithUV(0, 0, 0, 1, 1);
                    tess.addVertexWithUV(1, 0, 0, 0, 1);
                    tess.addVertexWithUV(1, 1, 0, 0, 0);
                    tess.addVertexWithUV(0, 1, 0, 1, 0);
                    tess.draw();
                }else{
                    long time = System.currentTimeMillis();
                    for(int i = 0; i < CustomFlags.flag_sections; i++){

                        double zOffset1 = getZLevel((float)i/CustomFlags.flag_sections, 3, time);
                        double zOffset2 = getZLevel((float)(i+1)/CustomFlags.flag_sections, 3, time);

                        tess.startDrawingQuads();
                        tess.addVertexWithUV((float)(i)/CustomFlags.flag_sections, 1, zOffset1, (float)(i+1)/CustomFlags.flag_sections, 0);
                        tess.addVertexWithUV((float)(1+i)/CustomFlags.flag_sections, 1, zOffset2, (float)(i)/CustomFlags.flag_sections, 0);
                        tess.addVertexWithUV((float)(1+i)/CustomFlags.flag_sections, 0, zOffset2, (float)(i)/CustomFlags.flag_sections, 1);
                        tess.addVertexWithUV((float)(i)/CustomFlags.flag_sections, 0, zOffset1, (float)(i+1)/CustomFlags.flag_sections, 1);
                        tess.draw();

                        tess.startDrawingQuads();
                        tess.addVertexWithUV((float)(i)/CustomFlags.flag_sections, 0, zOffset1, (float)(i+1)/CustomFlags.flag_sections, 1);
                        tess.addVertexWithUV((float)(i+1)/CustomFlags.flag_sections, 0, zOffset2, (float)(i)/CustomFlags.flag_sections, 1);
                        tess.addVertexWithUV((float)(i+1)/CustomFlags.flag_sections, 1, zOffset2, (float)(i)/CustomFlags.flag_sections, 0);
                        tess.addVertexWithUV((float)(i)/CustomFlags.flag_sections, 1, zOffset1, (float)(i+1)/CustomFlags.flag_sections, 0);
                        tess.draw();


                    }


                }

                break;
            case EQUIPPED:

                Minecraft.getMinecraft().renderEngine.func_110577_a(map_background);
                tess.startDrawingQuads();
                tess.addVertexWithUV(0, 1, 0, 1, 0);
                tess.addVertexWithUV(1, 1, 0, 0, 0);
                tess.addVertexWithUV(1, 0, 0, 0, 1);
                tess.addVertexWithUV(0, 0, 0, 1, 1);
                tess.draw();

                ImageCahce.setTexture(item);


                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

                tess.startDrawingQuads();
                tess.addVertexWithUV(1F/16F, 15F/16F, 0.001, 1, 0);
                tess.addVertexWithUV(15F/16F, 15F/16F, 0.001, 0, 0);
                tess.addVertexWithUV(15F/16F, 1F/16F, 0.001, 0, 1);
                tess.addVertexWithUV(1F/16F, 1F/16F, 0.001, 1, 1);
                tess.draw();

                tess.startDrawingQuads();
                tess.addVertexWithUV(1F/16F, 15F/16F, -0.001, 1, 0);
                tess.addVertexWithUV(15F/16F, 15F/16F, -0.001, 0, 0);
                tess.addVertexWithUV(15F/16F, 1F/16F, -0.001, 0, 1);
                tess.addVertexWithUV(1F/16F, 1F/16F, -0.001, 1, 1);
                tess.draw();

                GL11.glDisable(GL11.GL_BLEND);


                break;
            case EQUIPPED_FIRST_PERSON:

                ImageCahce.setTexture(item);

                tess.startDrawingQuads();
                tess.addVertexWithUV(0, 1, 0, 1, 0);
                tess.addVertexWithUV(1, 1, 0, 0, 0);
                tess.addVertexWithUV(1, 0, 0, 0, 1);
                tess.addVertexWithUV(0, 0, 0, 1, 1);
                tess.draw();


                break;
            case INVENTORY:

                Minecraft.getMinecraft().renderEngine.func_110577_a(map_background);
                tess.startDrawingQuads();
                tess.addVertexWithUV(0, 16, 0, 0, 1);
                tess.addVertexWithUV(16, 16, 0, 1, 1);
                tess.addVertexWithUV(16, 0, 0, 1, 0);
                tess.addVertexWithUV(0, 0, 0, 0, 0);
                tess.draw();

                ImageCahce.setTexture(item);

                tess.startDrawingQuads();
                tess.addVertexWithUV(2, 14, 0, 0, 1);
                tess.addVertexWithUV(14, 14, 0, 1, 1);
                tess.addVertexWithUV(14, 2, 0, 1, 0);
                tess.addVertexWithUV(2, 2, 0, 0, 0);
                tess.draw();

                break;
            case FIRST_PERSON_MAP:

                ImageCahce.setTexture(item);
                tess.startDrawingQuads();
                tess.addVertexWithUV(-0,128,-0.009999999776482582D,0,1);
                tess.addVertexWithUV(128,128,-0.009999999776482582D,1,1);
                tess.addVertexWithUV(128,-0,-0.009999999776482582D,1,0);
                tess.addVertexWithUV(-0,-0,-0.009999999776482582D,0,0);
                tess.draw();

                break;
        }

        GL11.glDisable(GL11.GL_BLEND);

    }
}
