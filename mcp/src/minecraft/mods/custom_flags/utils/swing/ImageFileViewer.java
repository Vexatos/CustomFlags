package mods.custom_flags.utils.swing;

import mods.custom_flags.utils.Utils;

import javax.swing.*;
import javax.swing.filechooser.FileView;
import java.io.File;

/**
 * Created by Aaron on 4/08/13.
 */
public class ImageFileViewer extends FileView {

    public Icon getIcon(File f) {
        String extension = Utils.getExtention(f.getName());
        if(extension != null){

            if()

        }else{
            return super.getIcon(f);
        }

        Icon icon = null;

        if (extension != null) {
            if (extension.equals(Utils.jpeg) ||
                    extension.equals(Utils.jpg)) {
                icon = jpgIcon;
            } else if (extension.equals(Utils.gif)) {
                icon = gifIcon;
            } else if (extension.equals(Utils.tiff) ||
                    extension.equals(Utils.tif)) {
                icon = tiffIcon;
            } else if (extension.equals(Utils.png)) {
                icon = pngIcon;
            }
        }
        return icon;
    }
}
