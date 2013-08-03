package mods.custom_flags.utils.swing;

import mods.custom_flags.utils.Utils;
import net.minecraft.util.Util;

import javax.swing.filechooser.FileFilter;
import java.io.File;
/**
 * Created by Aaron on 3/08/13.
 */
public class ImageFilter extends FileFilter {
    @Override
    public boolean accept(File pathname) {

        String extention = Utils.getExtention(pathname.getName());


        return extention == null ||
                extention.equalsIgnoreCase("png") ||
                extention.equalsIgnoreCase("tiff") ||
                extention.equalsIgnoreCase("tif") ||
                extention.equalsIgnoreCase("gif") ||
                extention.equalsIgnoreCase("bmp") ||
                extention.equalsIgnoreCase("jpeg") ||
                extention.equalsIgnoreCase("jpg");
    }

    @Override
    public String getDescription() {
        return "Images";
    }




}
