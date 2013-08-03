package mods.custom_flags.utils.swing;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by Aaron on 3/08/13.
 */
public class ImageFilter implements FileFilter {
    @Override
    public boolean accept(File pathname) {

        String extention = getExtention(pathname.getName());

        return extention.equalsIgnoreCase(".png") ||
                extention.equalsIgnoreCase(".tiff") ||
                extention.equalsIgnoreCase(".tif") ||
                extention.equalsIgnoreCase(".gif") ||
                extention.equalsIgnoreCase(".bmp") ||
                extention.equalsIgnoreCase(".jpeg") ||
                extention.equalsIgnoreCase(".jpg");
    }



    private static String getExtention(String pathname) {

        int index = pathname.lastIndexOf('.');

        if(index >= 0){
            return pathname.substring(index);
        }else{
            return null;
        }
    }

}
