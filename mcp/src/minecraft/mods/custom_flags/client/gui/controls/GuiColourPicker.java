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

    static{
        int[] pixels = hue_buffer.func_110565_c();
        for(int i = 0; i < pixels.length; i++){
            pixels[i] = Color.getHSBColor((float)i / (float)RES, 1, 1).getRGB() | 0xFF000000;
        }

        pixels = background_buffer.func_110565_c();

        for(int x = 0; x < )
        for(int i = 0; i < pixels.length; i++){
            pixels[i] =
        }
    }



}
