package mods.custom_flags.client;

import mods.custom_flags.client.gui.controls.GuiColourPicker;
import net.minecraft.client.gui.GuiScreen;

/**
 * Created by Aaron on 3/08/13.
 */
public class GuiFlagDesigner extends GuiScreen{

    private GuiColourPicker colourPicker;

    private static final int ID_SAVE = 0;
    private static final int ID_LOAD = 1;
    private static final int ID_COLOUR_PICKER = 2;
    private int guiLeft, guiTop, xSize, ySize;

    private static final int canvusMult = 6;
    private static final int canvusSize = canvusMult * 32;

    @Override
    public void initGui() {
        super.initGui();

        this.xSize = 80 + 10 + canvusSize + 10 + 80;
        this.ySize = canvusSize + 20;

        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - ySize) / 2;


        colourPicker = new GuiColourPicker(ID_COLOUR_PICKER, 100+canvusSize+guiLeft, guiTop, 0xFF000000, 7);

        this.buttonList.add(colourPicker);

    }
}
