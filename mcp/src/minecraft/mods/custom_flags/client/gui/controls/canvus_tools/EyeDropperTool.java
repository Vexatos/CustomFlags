package mods.custom_flags.client.gui.controls.canvus_tools;

import net.minecraft.client.renderer.texture.DynamicTexture;

/**
 * Created by Aaron on 3/08/13.
 */
public class EyeDropperTool implements ITool {
    @Override
    public void drawOverlay(int x, int y, int[] pixelsCurrent, DynamicTexture overlay, int rgb, boolean shift) {
        overlay.func_110564_a();
    }

    @Override
    public void draw(int x, int y, int[] pixelsCurrent, int rgb, boolean shift) {
    }
}
