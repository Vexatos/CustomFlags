package mods.custom_flags.utils;

/**
 * Created by Aaron on 3/08/13.
 */
public class Utils {

    public static String getExtention(String pathname) {

        int index = pathname.lastIndexOf('.');

        if(index >= 0){
            return pathname.substring(index+1);
        }else{
            return null;
        }
    }
}
