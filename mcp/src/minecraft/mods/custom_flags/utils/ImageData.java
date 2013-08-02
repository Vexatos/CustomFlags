package mods.custom_flags.utils;

import net.minecraft.item.ItemDye;

/**
 * User: nerd-boy
 * Date: 2/08/13
 * Time: 1:57 PM
 */
public class ImageData {

    public static final int IMAGE_RES = 32;
    private int[] pixels;


    public static ImageData defaultImage = new ImageData(ItemDye.dyeColors[]);


    public ImageData(int c1, int c2, int c3, int c4){
        pixels = new int[IMAGE_RES * IMAGE_RES];

        for(int y = 0; y < IMAGE_RES / 2; y++){

            for(int x = 0; x < IMAGE_RES / 2; x++){
                pixels[x+IMAGE_RES*y] = c1;
            }

            for(int x = IMAGE_RES/2; x < IMAGE_RES; x++){
                pixels[x+IMAGE_RES*y] = c2;
            }

        }
        for(int y = IMAGE_RES/2; y < IMAGE_RES; y++){

            for(int x = 0; x < IMAGE_RES / 2; x++){
                pixels[x+IMAGE_RES*y] = c3;
            }

            for(int x = IMAGE_RES/2; x < IMAGE_RES; x++){
                pixels[x+IMAGE_RES*y] = c4;
            }

        }
    }
}
