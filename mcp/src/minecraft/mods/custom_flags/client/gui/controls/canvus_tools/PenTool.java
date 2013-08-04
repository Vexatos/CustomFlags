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

        if(shift){
            drawLine(x, last_x, y, last_y, pixelsOverlay, rgb);
        }else{
            if (x > -1 &&  x < ImageData.IMAGE_RES && y > -1 && y < ImageData.IMAGE_RES){
                pixelsOverlay[x+ImageData.IMAGE_RES*y] = rgb;
            }
        }

        overlay.func_110564_a();
    }

    @Override
    public void draw(int x, int y, DynamicTexture current, int rgb, boolean shift) {
        int[] pixelsCurrent = current.func_110565_c();

        if(shift){

            drawLine(x, last_x, y, last_y, pixelsCurrent, rgb);

        }else{
            if (x > -1 &&  x < ImageData.IMAGE_RES && y > -1 && y < ImageData.IMAGE_RES){
                pixelsCurrent[x+ImageData.IMAGE_RES*y] = rgb;
            }
        }

        last_x = x;
        last_y = y;
    }

    private void drawLine(int x0, int x1, int y0, int y1, int[] pixelsCurrent, int rgb) {

        boolean steep = Math.abs(y1 - y0) > Math.abs(x1- x0);
        if(steep){
            int temp = x0;
            x0 = x1;
            x1 = temp;

            temp = y0;
            y0 = y1;
            y1 = temp;
        }
        if(x0 > x1){
            int temp = x0;
            x0 = x1;
            x1 = temp;

            temp = y0;
            y0 = y1;
            y1 = temp;
        }

        int deltaX = x1 - x0;
        int deltaY = Math.abs(y1 - y0);

        int error = deltaX / 2;
        int yStep = y0<y1?1:-1;
        int y = y0;
        for(int x = x0; x < x1; x++){
            if (x > -1 &&  x < ImageData.IMAGE_RES && y > -1 && y < ImageData.IMAGE_RES){
                if(steep){
                    pixelsCurrent[y+ImageData.IMAGE_RES*x] = rgb;
                }else{
                    pixelsCurrent[x+ImageData.IMAGE_RES*y] = rgb;
                }
            }

            error = error - deltaY;
            if(error < 0){
                y = y + yStep;
                error = error - deltaX;
            }
        }

    }


}
