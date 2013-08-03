package mods.custom_flags.client.gui.controls;

import net.minecraft.client.gui.GuiButton;

/**
 * Created by Aaron on 3/08/13.
 */
public class GuiToggleButton extends GuiButton{


    public GuiToggleButton(int id, int x, int y, int w, int h, String label, boolean isOn) {
        super(id, x, y, w, h, label);

        enabled = !isOn;
    }

    public boolean isOn(){
        return !enabled;
    }

    public void setToggle(boolean toggle){
        enabled = !toggle;
    }
}
