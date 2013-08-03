package mods.custom_flags.client.gui.controls.canvus_tools;

import mods.custom_flags.utils.ImageData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;

/**
 * Created by Aaron on 3/08/13.
 */
public class PenTool implements ITool {
    @Override
    public void drawOverlay(int x, int y, DynamicTexture current, DynamicTexture overlay, int rgb, boolean shift) {
        int[] pixelsOverlay = overlay.func_110565_c();
        int[] pixelsCurrent = current.func_110565_c();

        for(int i = 0; i < pixelsOverlay.length; i++){
            pixelsOverlay[i] = i==x+ImageData.IMAGE_RES*y ? rgb : pixelsCurrent[i];
        }

        overlay.func_110564_a();
    }

    @Override
    public void draw(int x, int y, DynamicTexture current, int rgb, boolean shift) {
        int[] pixelsCurrent = current.func_110565_c();



        if(x+ImageData.IMAGE_RES*y < pixelsCurrent.length){
            pixelsCurrent[x+ImageData.IMAGE_RES*y] = rgb;
        }

    }


}
