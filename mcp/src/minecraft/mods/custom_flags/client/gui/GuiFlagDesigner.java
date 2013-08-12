package mods.custom_flags.client.gui;

import cpw.mods.fml.common.network.PacketDispatcher;
import mods.custom_flags.CustomFlags;
import mods.custom_flags.client.gui.controls.GuiColourPicker;
import mods.custom_flags.client.gui.controls.GuiSliderAlt;
import mods.custom_flags.client.gui.controls.GuiToggleButton;
import mods.custom_flags.client.gui.controls.canvus_tools.*;
import mods.custom_flags.items.ItemFlag;
import mods.custom_flags.packet.UpdateHeldFlagImagePacket;
import mods.custom_flags.utils.ImageData;
import mods.custom_flags.utils.Utils;
import mods.custom_flags.utils.swing.ImageFileViewer;
import mods.custom_flags.utils.swing.ImageFilter;
import mods.custom_flags.utils.swing.ImagePreviewPanel;
import mods.custom_flags.utils.swing.ImageSplitDialog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Cursor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.IntBuffer;
import java.util.Arrays;

/**
 * Created by Aaron on 3/08/13.
 */
public class GuiFlagDesigner extends GuiScreen{

    private GuiColourPicker colourPicker;
    private GuiToggleButton[] toggleButtons;
    private ITool[] tools;
    private ITool selectedTool;
    private GuiSliderAlt slider;

    private static final int ID_SAVE = 0;
    private static final int ID_LOAD = 1;
    private static final int ID_OK = 2;
    private static final int ID_LOAD_SECTION = 3;
    private static final int ID_COLOUR_PICKER = 4;
    private static final int SLIDER = 5;
    private int guiLeft, guiTop, xSize, ySize;

    private static final int canvusMult = 5;
    private static final int canvusSize = canvusMult * 32;

    private static final DynamicTexture canvus_back = new DynamicTexture(2,2);
    //private static final DynamicTexture current = new DynamicTexture(ImageData.IMAGE_RES, ImageData.IMAGE_RES);
    private static final DynamicTexture overlay = new DynamicTexture(ImageData.IMAGE_RES, ImageData.IMAGE_RES);

    private JFileChooser fc;

    //private DummyDialog dialog;


    private EntityPlayer player;

    private int[][] imageBuffer = new int[CustomFlags.BUFFER_SIZE][];
    private int bufferPointer = 0;

    private Cursor[] cursors;

    static{
        int[] pixels = canvus_back.func_110565_c();
        pixels[0] = 0xFF666666;
        pixels[1] = 0xFF999999;
        pixels[2] = 0xFF999999;
        pixels[3] = 0xFF666666;
    }

    private int toolIndex = 0;

    public GuiFlagDesigner(EntityPlayer player) {
        this.player = player;

        //dialog = new DummyDialog(null);

        fc = new JFileChooser() {
            @Override
            protected JDialog createDialog(Component parent) throws HeadlessException {
                // intercept the dialog created by JFileChooser
                JDialog dialog = super.createDialog(parent);
                dialog.setModal(true);  // set modality (or setModalityType)
                dialog.setAlwaysOnTop(true);
                return dialog;
            }
        };
        fc.setFileFilter(new ImageFilter());
        fc.setAcceptAllFileFilterUsed(false);
        if(CustomFlags.FcLoadImages){
            fc.setFileView(new ImageFileViewer());
            ImagePreviewPanel preview = new ImagePreviewPanel();
            fc.setAccessory(preview);
            fc.addPropertyChangeListener(preview);
        }


        imageBuffer = new int[CustomFlags.BUFFER_SIZE][];
        imageBuffer[bufferPointer] =  new int[ImageData.IMAGE_RES * ImageData.IMAGE_RES];
        ItemStack item = player.getHeldItem();
        if(item != null && item.getItem() instanceof ItemFlag){
            if(((ItemFlag) item.getItem()).hasImageData(item)){
                ImageData image = new ImageData(((ItemFlag) item.getItem()).getImageData(item));
                image.setTexture(imageBuffer[bufferPointer]);
            }else{
                ImageData.defaultImage.setTexture(imageBuffer[bufferPointer]);
            }
        }else{
            ImageData.defaultImage.setTexture(imageBuffer[bufferPointer]);
        }

    }

    @Override
    protected void keyTyped(char par1, int par2) {
        super.keyTyped(par1, par2);

        if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)){

            int x = (Mouse.getEventX() * this.width / this.mc.displayWidth -90 -guiLeft)/canvusMult;
            int y = (this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1 - 25- guiTop)/canvusMult;

            if(par2 == Keyboard.KEY_Z){
                int prev = (bufferPointer+CustomFlags.BUFFER_SIZE-1) % CustomFlags.BUFFER_SIZE;
                if(imageBuffer[prev]==null) {
                    Toolkit.getDefaultToolkit().beep();
                }else{
                    bufferPointer = prev;
                    selectedTool.drawOverlay(x,y,imageBuffer[bufferPointer],overlay,ImageData.roundColour(colourPicker.getRGB()), Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT));
                }
            }else if (par2 == Keyboard.KEY_Y){
                int next = (bufferPointer+1) % CustomFlags.BUFFER_SIZE;
                if(imageBuffer[next] == null) {
                    Toolkit.getDefaultToolkit().beep();
                }else{
                    bufferPointer = next;
                    selectedTool.drawOverlay(x,y,imageBuffer[bufferPointer],overlay,ImageData.roundColour(colourPicker.getRGB()), Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT));
                }
            }
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

        colourPicker = new GuiColourPicker(ID_COLOUR_PICKER, 100+canvusSize+guiLeft, guiTop+25, 0xFF000000, 7);

        this.buttonList.add(new GuiButton(ID_OK, guiLeft+100+canvusSize, guiTop+canvusSize+5, 80, 20, StatCollector.translateToLocal("button.ok")));
        this.buttonList.add(new GuiButton(ID_SAVE, guiLeft + 70, guiTop, 75, 20,StatCollector.translateToLocal( "button.save")));
        this.buttonList.add(new GuiButton(ID_LOAD, guiLeft + 70+80, guiTop, 75, 20, StatCollector.translateToLocal("button.load")));
        this.buttonList.add(new GuiButton(ID_LOAD_SECTION, guiLeft + 70+80+80, guiTop, 75, 20, StatCollector.translateToLocal("button.load.sections")));
        this.buttonList.add(colourPicker);

        tools = new ITool[5];
        toggleButtons = new GuiToggleButton[tools.length];
        tools[0] = new PenTool();
        tools[1] = new RectangleTool();
        tools[2] = new CircleTool();
        tools[3] = new FloodFillTool();
        tools[4] = new EyeDropperTool();
        cursors = new Cursor[tools.length+1];
        cursors[0] = Mouse.getNativeCursor();

        for(int i = 0; i < toggleButtons.length; i++){
            toggleButtons[i] = new GuiToggleButton(10+ i, guiLeft+60, guiTop+25+i*22, 20, 20, StatCollector.translateToLocal(tools[i].getToolName()), i==0, tools[i].getToolImage());
            this.buttonList.add(toggleButtons[i]);

            try{
                int[] rgbs = TextureUtil.func_110986_a(Minecraft.getMinecraft().func_110442_L(), tools[i].getToolImage());
                IntBuffer buffer = IntBuffer.wrap(rgbs);
                cursors[i+1] = new Cursor(16, 16, i==1||i==2?8:0, i==1||i==2?8:0, 1, buffer, null);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        selectedTool = tools[0];

        slider = new GuiSliderAlt(SLIDER,  guiLeft, guiTop+25+5*22, 80, StatCollector.translateToLocal("gui.threshold"), 0, 0 , 64);

       //this.buttonList.add(slider);

        slider.enabled = false;
        slider.drawButton = false;
    }

    @Override
    public void updateScreen() {
        int x = (Mouse.getEventX() * this.width / this.mc.displayWidth -90 -guiLeft)/canvusMult;
        int y = (this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1 - 25- guiTop)/canvusMult;

        try{
        if(x >= 0 && x < ImageData.IMAGE_RES && y >= 0 && y < ImageData.IMAGE_RES){
            Mouse.setNativeCursor(cursors[toolIndex+1]);
        }else{
            Mouse.setNativeCursor(cursors[0]);
        }
        }catch (Exception e){
            e.printStackTrace();
        }

        super.updateScreen();
    }



    @Override
    protected void mouseClicked(int par1, int par2, int par3) {

        int x = (Mouse.getEventX() * this.width / this.mc.displayWidth -90 -guiLeft)/canvusMult;
        int y = (this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1 - 25- guiTop)/canvusMult;

        if(x >= 0 && x < ImageData.IMAGE_RES && y >= 0 && y < ImageData.IMAGE_RES){
            if(selectedTool instanceof EyeDropperTool){
                colourPicker.selectColour(imageBuffer[bufferPointer][x+ImageData.IMAGE_RES*y]);
            }else if (selectedTool instanceof RectangleTool){
                ((RectangleTool) selectedTool).last_x = x;
                ((RectangleTool) selectedTool).last_y = y;
            }else{
                int next = (bufferPointer+1) % CustomFlags.BUFFER_SIZE;
                imageBuffer[next] = Arrays.copyOf(imageBuffer[bufferPointer], ImageData.IMAGE_RES * ImageData.IMAGE_RES);
                bufferPointer = next;

                //Clear the next
                imageBuffer[(bufferPointer+1) % CustomFlags.BUFFER_SIZE] = null;

                selectedTool.draw(x, y, imageBuffer[bufferPointer], ImageData.roundColour(colourPicker.getRGB()), Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT));
            }
        }else if (selectedTool instanceof RectangleTool){
            ((RectangleTool) selectedTool).last_x = -1000;
            ((RectangleTool) selectedTool).last_y = -1000;
        }

        super.mouseClicked(par1, par2, par3);
    }



    @Override
    public void handleMouseInput() {

       super.handleMouseInput();

        int x = (Mouse.getEventX() * this.width / this.mc.displayWidth -90 -guiLeft)/canvusMult;
        int y = (this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1 - 25- guiTop)/canvusMult;

        if(! Mouse.getEventButtonState() && Mouse.getEventButton() == 0){
            if(selectedTool instanceof RectangleTool){
                int next = (bufferPointer+1) % CustomFlags.BUFFER_SIZE;
                imageBuffer[next] = Arrays.copyOf(imageBuffer[bufferPointer], ImageData.IMAGE_RES * ImageData.IMAGE_RES);
                bufferPointer = next;

                //Clear the next
                imageBuffer[(bufferPointer+1) % CustomFlags.BUFFER_SIZE] = null;

                selectedTool.draw(x, y, imageBuffer[bufferPointer], ImageData.roundColour(colourPicker.getRGB()), Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT));
            }
        }

        selectedTool.drawOverlay(x,y,imageBuffer[bufferPointer],overlay, ImageData.roundColour(colourPicker.getRGB()), Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT));

    }

    @Override
    protected void mouseClickMove(int par1, int par2, int par3, long par4) {
        super.mouseClickMove(par1, par2, par3, par4);

        int x = (Mouse.getEventX() * this.width / this.mc.displayWidth -90 -guiLeft)/canvusMult;
        int y = (this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1 - 25- guiTop)/canvusMult;

        if(Mouse.isButtonDown(0)){
            if(selectedTool instanceof EyeDropperTool){
                if (x > -1 &&  x < ImageData.IMAGE_RES && y > -1 && y < ImageData.IMAGE_RES){
                    colourPicker.selectColour(imageBuffer[bufferPointer][x+ImageData.IMAGE_RES*y]);
                }
            }else{
                selectedTool.draw(x, y, imageBuffer[bufferPointer], ImageData.roundColour(colourPicker.getRGB()), Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT));
            }
        }
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        int[] overPixels = overlay.func_110565_c();
        for(int i = 0; i < overPixels.length; i++){
            overPixels[i] = 0x00000000;
        }

        try{
           Mouse.setNativeCursor(cursors[0]);
        }catch (Exception e){
            e.printStackTrace();
        }
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

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        overlay.func_110564_a();
        drawTexturedModalRect(90 + guiLeft, 25 + guiTop, canvusSize, canvusSize, 0, 0, 1, 1);
        GL11.glDisable(GL11.GL_BLEND);

        super.drawScreen(par1, par2, par3);
    }


    @Override
    protected void actionPerformed(GuiButton par1GuiButton) {
        super.actionPerformed(par1GuiButton);

        if(par1GuiButton.id >=10 && par1GuiButton.id < 10+toggleButtons.length){
            for(int i = 0; i < toggleButtons.length; i++){
                toggleButtons[i].setToggle(i+10==par1GuiButton.id);
            }
            selectedTool = tools[par1GuiButton.id - 10];
            toolIndex =  par1GuiButton.id - 10;

            slider.enabled = selectedTool instanceof FloodFillTool;
            slider.drawButton = selectedTool instanceof FloodFillTool;
        }

        switch (par1GuiButton.id){
            case ID_OK:
                ItemStack stack = player.getCurrentEquippedItem();
                if(stack != null && stack.getItem() instanceof ItemFlag){
                    ((ItemFlag) stack.getItem()).setImageData(stack, new ImageData(imageBuffer[bufferPointer], ImageData.IMAGE_RES, ImageData.IMAGE_RES).getByteArray(), player.worldObj);
                    PacketDispatcher.sendPacketToServer(UpdateHeldFlagImagePacket.generatePacket(player.username, stack));
                    this.keyTyped('c',1);
                }
                break;
            case ID_SAVE:
                if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
                    BufferedImage image = new BufferedImage(ImageData.IMAGE_RES, ImageData.IMAGE_RES, BufferedImage.TYPE_4BYTE_ABGR);
                    int[] pixels = imageBuffer[bufferPointer];
                    for(int x = 0; x < image.getWidth(); x++){
                        for(int y = 0; y < image.getHeight(); y++){
                            image.setRGB(x, y, pixels[x+ImageData.IMAGE_RES*y]);
                        }
                    }

                    try {

                        File f = fc.getSelectedFile();
                        if(Utils.getExtention(f.getName()) == null){
                            f = new File(f.getParentFile(), f.getName()+".png");
                        }

                        f.createNewFile();

                        ImageIO.write(image, "png", f);

                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
                break;
            case ID_LOAD:
                if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    try{
                        ImageData image = new ImageData(ImageIO.read(fc.getSelectedFile()), ImageData.IMAGE_RES, ImageData.IMAGE_RES);
                        image.setTexture(imageBuffer[bufferPointer]);

                        int next = (bufferPointer+1) % CustomFlags.BUFFER_SIZE;
                        imageBuffer[next] = Arrays.copyOf(imageBuffer[bufferPointer], ImageData.IMAGE_RES * ImageData.IMAGE_RES);
                        bufferPointer = next;
                        imageBuffer[(bufferPointer+1) % CustomFlags.BUFFER_SIZE] = null;

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
            case ID_LOAD_SECTION:
                if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    try{
                        BufferedImage original = ImageIO.read(fc.getSelectedFile());

                        ImageSplitDialog dialog = new ImageSplitDialog(original);
                        dialog.setLocationRelativeTo(null);
                        dialog.setVisible(true);

                        if(dialog.imageSection != null){
                            ImageData image = new ImageData(
                                    dialog.imageSection,
                                    ImageData.IMAGE_RES, ImageData.IMAGE_RES);

                            image.setTexture(imageBuffer[bufferPointer]);

                            int next = (bufferPointer+1) % CustomFlags.BUFFER_SIZE;
                            imageBuffer[next] = Arrays.copyOf(imageBuffer[bufferPointer], ImageData.IMAGE_RES * ImageData.IMAGE_RES);
                            bufferPointer = next;
                            imageBuffer[(bufferPointer+1) % CustomFlags.BUFFER_SIZE] = null;
                        }

                        dialog.dispose();



                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
            case SLIDER:
                ((FloodFillTool)tools[1]).threshold = slider.getValue();
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
