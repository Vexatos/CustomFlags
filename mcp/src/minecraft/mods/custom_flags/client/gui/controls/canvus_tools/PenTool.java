package mods.custom_flags.client.gui.controls.canvus_tools;

import mods.custom_flags.utils.ImageData;
import net.minecraft.client.renderer.texture.DynamicTexture;

/**
 * Created by Aaron on 3/08/13.
 */
public class PenTool implements ITool {

    private int last_x = ImageData.IMAGE_RES / 2;
    private int last_y = ImageData.IMAGE_RES / 2;

    @Override
    public void drawOverlay(int x, int y, DynamicTexture current, DynamicTexture overlay, int rgb, boolean shift) {
        int[] pixelsOverlay = overlay.func_110565_c();
        int[] pixelsCurrent = current.func_110565_c();

        for(int i = 0; i < pixelsOverlay.length; i++){
            pixelsOverlay[i] = pixelsCurrent[i];
        }

        if (x > -1 &&  x < ImageData.IMAGE_RES && y > -1 && y < ImageData.IMAGE_RES){
            pixelsOverlay[x+ImageData.IMAGE_RES*y] = rgb;
        }

        overlay.func_110564_a();
    }

    @Override
    public void draw(int x, int y, DynamicTexture current, int rgb, boolean shift) {
        int[] pixelsCurrent = current.func_110565_c();

        if(shift){

            

        }else{
            if (x > -1 &&  x < ImageData.IMAGE_RES && y > -1 && y < ImageData.IMAGE_RES){
                pixelsCurrent[x+ImageData.IMAGE_RES*y] = rgb;
            }
        }

        last_x = x;
        last_y = y;
    }


}
