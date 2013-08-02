package mods.custom_flags.client.gui.controls;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.texture.DynamicTexture;

import java.awt.*;

/**
 * User: nerd-boy
 * Date: 2/08/13
 * Time: 12:20 PM
 * TODO: Add discription
 */
public class GuiColourPicker extends GuiButton {

    public static final int RES = 48;

    public static final DynamicTexture sb_buffer = new DynamicTexture(RES, RES);
    public static final DynamicTexture hue_buffer = new DynamicTexture(1, RES);
    public static final DynamicTexture background_buffer = new DynamicTexture(3, 11);

    public static final int DEFAULT_COLOURS = 1;
    public static final int ALPHA_SELECTION = 2;
    public static final int COLOUR_DISPLAY = 4;

    static{
        int[] pixels = hue_buffer.func_110565_c();
        for(int i = 0; i < pixels.length; i++){
            pixels[i] = Color.getHSBColor((float)i / (float)RES, 1, 1).getRGB() | 0xFF000000;
        }

        pixels = background_buffer.func_110565_c();

        for(int x = 0; x < 3; x++){
            for(int y = 0; y < 11; y++){
                pixels[x*y+y] = (x%2) == (y%2) ? 0xFF666666 : 0xFF999999;
            }
        }

    }


    private int selectedRGB;
    private float[] selectedHSB;
    private float selected_alpha;

    private int sb_start_x;
    private int sb_start_y;

    private int hue_start_x;

    private int alpha_start_x;

    private int type;


    public GuiColourPicker(int id, int x, int y, int rgb, int type){
        super(id, x, y,
                (type&ALPHA_SELECTION)==ALPHA_SELECTION?80:64,
                48 + (type&DEFAULT_COLOURS)==DEFAULT_COLOURS?0:16 + (type&COLOUR_DISPLAY)==COLOUR_DISPLAY?0:16,
                ""
        );

        this.type = type;

        sb_start_x = x;
        sb_start_y = y;

        if(isSwitchOn(DEFAULT_COLOURS)){
            sb_start_y += 16;
        }

        hue_start_x = sb_start_x + 52;

        alpha_start_x = hue_start_x + 16;

        selectColour(rgb);
    }

    private void calculateBuffers() {
        int[] pixels = hue_buffer.func_110565_c();
        for(int s = 0; s < RES; s++){
            for(int b = 0; b < RES; b++){
                pixels[s * b + b] =
            }
        }
    }

    private void selectColour(int rgb) {
        this.selectedRGB = rgb;
        this.selectedHSB = Color.RGBtoHSB((rgb&0x00FF0000) >> 16, (rgb&0x0000FF00) >> 8, (rgb&0x000000FF), new float[3]);
        selected_alpha = (float)((rgb &0xFF000000) >> 24) / 255F;
        calculateBuffers();
    }


    private boolean isSwitchOn(int switchMask){
        return (type & switchMask) == switchMask;
    }
}
