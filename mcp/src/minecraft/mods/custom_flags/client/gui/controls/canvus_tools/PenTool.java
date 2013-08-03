package mods.custom_flags.client.gui.controls.canvus_tools;

import net.minecraft.client.renderer.texture.DynamicTexture;

/**
 * Created by Aaron on 3/08/13.
 */
public class PenTool implements ITool {
    @Override
    public void drawOverlay(int x, int y, DynamicTexture overlay, int rgb, boolean shift) {
        int[] pixels = overlay.func_110565_c();
        for(int i = 0; i < pixels.length; i++){
            pixels[i] =
        }
    }
}
