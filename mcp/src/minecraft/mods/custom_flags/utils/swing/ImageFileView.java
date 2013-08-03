package mods.custom_flags.utils.swing;

import mods.custom_flags.utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileView;
import java.io.File;

/**
 * Created by Aaron on 3/08/13.
 */
public class ImageFileView extends FileView{

    @Override
    public Icon getIcon(File f) {
        if(Utils.getExtention(f.getName()) != null){
            try{
                return new ImageIcon(ImageIO.read(f));
            }catch (Exception e){
                e.printStackTrace();
                return super.getIcon(f);
            }
        }else
            return super.getIcon(f);
    }


}
