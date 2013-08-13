package mods.custom_flags;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.DamageSource;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * User: nerd-boy
 * Date: 13/08/13
 * Time: 4:13 PM
 * TODO: Add discription
 */
public class GiveInstructionsCommand extends CommandBase {


    public String getCommandName()
    {
        return "cf.instructions";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
        return "commands.cf.instr.usage";
    }

    public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
        EntityPlayerMP entityplayermp = getCommandSenderAsPlayer(par1ICommandSender);

        ItemStack instructions = new ItemStack(Item.writtenBook);
        NBTTagCompound nbt =  new NBTTagCompound();
        nbt.setString("title", "Custom Flags Instructions");
        nbt.setString("author", "Nerd-boy");

        NBTTagList pages = new NBTTagList("pages");

        try{
        BufferedReader br = new BufferedReader(new FileReader(CustomFlags.class.getResource("CustomFlagsInstructions").getFile()));

        int page = 1;
        StringBuffer sb = new StringBuffer();
        while(br.ready()){
            String nextLine = br.readLine();
            System.out.println(nextLine);

            if(nextLine.equals("[pb]")){
                pages.appendTag(new NBTTagString(String.valueOf(page), sb.toString()));
                sb = new StringBuffer();
                page++;
            }else{
                sb.append(nextLine);
                sb.append('\n');
            }

        }



            /*
        pages.appendTag(new NBTTagString("1", "Custom Flags\n  - A ModJam mod by Nerd-boy\n\nHave you ever had an awesome design but wished you could show it off in Minecraft?\nWell now you can in beautifully animated flags"));
        pages.appendTag(new NBTTagString("2", "Flag Pole\nFlag poles are created by placing 3 wooden logs or 3 iron ingots in a vertical line in the crafting recipe\nHorizontal flags are created by placing it on the top or bottom of a block, vertical flags are created by hanging it on the side"));
        pages.appendTag(new NBTTagString("3", "Flags\nFlags are created by arranging 4 wool blocks (of any colour) in a 2 x 2 square in the crafting grid. Flags may be attached to any flag pole by right clicking a flag pole while holding a flag. Each flag pole block can have a maximum of 4 flags attached to it"));

        pages.appendTag(new NBTTagString("4", "Flag Designer\nRight clicking while holding a flag (with no usable block in front of the player) will open the flag designer gui. The GUI supports a 32x32 12 bit ARGB image. A colour chooser is included on the right of the screen and drawing tools on the left The tools include"));

        pages.appendTag(new NBTTagString("5", "Ok button\nThe OK button will save the design to the currently held flag, pressing escape will close the flag designer without changing the design"));
        pages.appendTag(new NBTTagString("6", "Pen Tool\nDraws a single pixel at the current mouse location when the left mouse button is down. Holding shift will draw a straight line from the last location clicked to the current mouse location"));
        pages.appendTag(new NBTTagString("7", "Flood Fill\nClicking the left mouse button will change all of the connected pixals of the same colour to the currently selected colour"));
        pages.appendTag(new NBTTagString("8", "Rectangle\nDraws a rectangle with the corners of the last 2 clicked locations. Holding shift will force the rectangle into a square"));
        pages.appendTag(new NBTTagString("9", "Oval\n Draws an oval with the corners of the last 2 clicked locations. Holding shift will force the oval into a circle"));
        pages.appendTag(new NBTTagString("10", "Text Tool\nAllows the user to write text at the most recently clicked location. Pressing enter saves the text"));
        pages.appendTag(new NBTTagString("11", "Color Picker\nClicking will set the current colour to the colour of the selected pixal"));
        pages.appendTag(new NBTTagString("12", "Undo/Redo\nPressing ctrl-z will undo the last action (maximum of 10). ctrl-y will redo"));
        pages.appendTag(new NBTTagString("13", "Save\nOpens a save dialog to allow the user to save the current design to their filesystem for later use"));
        pages.appendTag(new NBTTagString("13", "Load\nOpens a load dialog to allow the user to use an image on their current file system as their current flag. Images will be scaled to 32x32 and converted to 12bit ARGB before being drawn on the canvus"));
        pages.appendTag(new NBTTagString("14", "Load Section\nOpens a load dialog to allow the user to use a section of an image as their current flag. After selecting the desired file, a user will be asked how many sections they desire in the X and Y directions and which section they want to use for the current flag"));
                       */
        }catch (Exception e){
            pages.appendTag(new NBTTagString("1", "Error while reading help file"));
        }

        nbt.setTag("pages", pages);


        instructions.setTagCompound(nbt);



        entityplayermp.inventory.addItemStackToInventory(instructions);
    }

}
