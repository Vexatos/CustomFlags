package mods.custom_flags.client.gui;

import mods.custom_flags.client.gui.controls.GuiColourPicker;
import mods.custom_flags.items.ItemFlag;
import mods.custom_flags.utils.ImageData;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

import javax.swing.*;

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

    private static final DynamicTexture canvus_back = new DynamicTexture(2,2);
    private static final DynamicTexture current = new DynamicTexture(ImageData.IMAGE_RES, ImageData.IMAGE_RES);

    private EntityPlayer player;

    static{
        int[] pixels = canvus_back.func_110565_c();
        pixels[0] = 0xFF666666;
        pixels[1] = 0xFF999999;
        pixels[2] = 0xFF999999;
        pixels[3] = 0xFF666666;
    }

    public GuiFlagDesigner(EntityPlayer player) {
        this.player = player;

        ItemStack item = player.getHeldItem();
        if(item != null && item.getItem() instanceof ItemFlag){
            if(((ItemFlag) item.getItem()).hasImageData(item)){
                ImageData image = new ImageData(((ItemFlag) item.getItem()).getImageData(item));
                image.setTexture(current.func_110565_c());
            }else{
                ImageData.defaultImage.setTexture(current.func_110565_c());
            }
        }else{
            ImageData.defaultImage.setTexture(current.func_110565_c());
        }
    }

    @Override
    public void initGui() {
        super.initGui();

        this.buttonList.clear();

        this.xSize = 80 + 10 + canvusSize + 10 + 80;
        this.ySize = canvusSize + 25;

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
        this.drawDefaultBackground();

        //Draw Canvas
        canvus_back.func_110564_a();
        drawTexturedModalRect(90 + guiLeft, 25+guiTop, canvusSize, canvusSize, 0, 0, 32, 32);

        current.func_110564_a();
        drawTexturedModalRect(90 + guiLeft, 25+guiTop, canvusSize, canvusSize, 0, 0, 1, 1);



        super.drawScreen(par1, par2, par3);
    }

    @Override
    protected void actionPerformed(GuiButton par1GuiButton) {
        super.actionPerformed(par1GuiButton);

        switch (par1GuiButton.id){
            case ID_LOAD:
                //JFileChooser fc = new JFileChooser(null);



                break;
        }
    }

    public void drawTexturedModalRect(int x, int y, int width, int height, int tex_x, int tex_y, int tex_width, int tex_height)
    {
        float f = 1F;
        float f1 = 1F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(x + 0), (double)(y + height), (double)this.zLevel, (double)((float)(tex_x + 0) * f), (double)((float)(tex_y + tex_height) * f1));
        tessellator.addVertexWithUV((double)(x + width), (double)(y + height), (double)this.zLevel, (double)((float)(tex_x + tex_width) * f), (double)((float)(tex_y + tex_height) * f1));
        tessellator.addVertexWithUV((double)(x + width), (double)(y + 0), (double)this.zLevel, (double)((float)(tex_x + tex_width) * f), (double)((float)(tex_y + 0) * f1));
        tessellator.addVertexWithUV((double)(x + 0), (double)(y + 0), (double)this.zLevel, (double)((float)(tex_x + 0) * f), (double)((float)(tex_y + 0) * f1));
        tessellator.draw();
    }

}
