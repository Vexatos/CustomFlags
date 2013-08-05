package mods.custom_flags.client.gui.controls.canvus_tools;

import mods.custom_flags.utils.ImageData;
import net.minecraft.client.renderer.texture.DynamicTexture;

/**
 * Created by Aaron on 3/08/13.
 */
public class FloodFillTool implements ITool {
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

        if (x > -1 &&  x < ImageData.IMAGE_RES && y > -1 && y < ImageData.IMAGE_RES){
            int[] pixelsCurrent = current.func_110565_c();
            if(shift){
                for(int i = 0; i < pixelsCurrent.length; i++){
                    pixelsCurrent[i] = rgb;
                }
            }else{

                int targetColour = pixelsCurrent[x+ImageData.IMAGE_RES*y];


                if(rgb != targetColour){
                    floodFill(x, y, pixelsCurrent, targetColour, rgb);
                }
            }
        }
    }

    private void floodFill(int x, int y, int[] pixals, int targetColour, int newColour) {

        if (x > -1 &&  x < ImageData.IMAGE_RES && y > -1 && y < ImageData.IMAGE_RES){

            if(pixals[x+ImageData.IMAGE_RES*y] == targetColour){
                pixals[x+ImageData.IMAGE_RES*y] = newColour;

                floodFill(x+1, y, pixals, targetColour, newColour);
                floodFill(x-1, y, pixals, targetColour, newColour);
                floodFill(x, y+1, pixals, targetColour, newColour);
                floodFill(x, y-1, pixals, targetColour, newColour);
            }

        }
    }
}
