package mods.custom_flags.client.gui;

import mods.custom_flags.client.gui.controls.GuiColourPicker;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

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

        this.buttonList.clear();

        this.xSize = 80 + 10 + canvusSize + 10 + 80;
        this.ySize = canvusSize + 20;

        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - ySize) / 2;


        colourPicker = new GuiColourPicker(ID_COLOUR_PICKER, 100+canvusSize+guiLeft, guiTop, 0xFF000000, 7);



        this.buttonList.add(new GuiButton(ID_SAVE, guiLeft + 90, guiTop, 80, 20, "button.save"));
        this.buttonList.add(new GuiButton(ID_LOAD, guiLeft + 10 + canvusSize, guiTop, 80, 20, "button.load"));
        this.buttonList.add(colourPicker);

    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3)
    {
        this.drawGradientRect(0, 0, this.width, this.height, 1615855616, -1602211792);



        super.drawScreen(par1, par2, par3);
    }

}
