package mods.custom_flags.client.gui.controls.canvus_tools;

import net.minecraft.client.renderer.texture.DynamicTexture;

/**
 * Created by Aaron on 3/08/13.
 */
public interface ITool {

    public void drawOverlay(int x, int y, DynamicTexture current, DynamicTexture overlay, int rgb, boolean shift);

    public void draw(int x, int y, DynamicTexture current, int rgb, boolean shift);

}
