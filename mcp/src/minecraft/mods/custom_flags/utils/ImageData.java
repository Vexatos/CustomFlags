package mods.custom_flags.utils;

import net.minecraft.item.ItemDye;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * User: nerd-boy
 * Date: 2/08/13
 * Time: 1:57 PM
 */
public class ImageData {

    public static final int IMAGE_RES = 32;
    private int[] pixels;

    public static final ImageData defaultImage;
    public static byte[] defaultData;


    static{
        defaultImage = new ImageData(0xFFFF0000, 0xFFFFFF00, 0xFF00FF00, 0xFF0000FF);
        defaultData = defaultImage.getByteArray();
    }



    public ImageData(int c1, int c2, int c3, int c4){
        pixels = new int[IMAGE_RES * IMAGE_RES];

        for(int y = 0; y < IMAGE_RES / 2; y++){

            for(int x = 0; x < IMAGE_RES / 2; x++){
                pixels[x*IMAGE_RES+y] = c1;
            }

            for(int x = IMAGE_RES/2; x < IMAGE_RES; x++){
                pixels[x*IMAGE_RES+y] = c3;
            }

        }
        for(int y = IMAGE_RES/2; y < IMAGE_RES; y++){

            for(int x = 0; x < IMAGE_RES / 2; x++){
                pixels[x*IMAGE_RES+y] = c2;
            }

            for(int x = IMAGE_RES/2; x < IMAGE_RES; x++){
                pixels[x*IMAGE_RES+y] = c4;
            }

        }
    }

    public ImageData(BufferedImage before, int width, int height){ //How we create the Image Data
        BufferedImage scaled = before; // Copy the Cuffered Image

        if(before.getWidth() != width || before.getHeight() != height){ //If the hight of the image is not our targert
            scaled = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); //Create a new empty image of the target size
            AffineTransform at = new AffineTransform(); //Create a new Affine Transform
            at.scale((float)width / before.getWidth(), (float)height / before.getHeight()); //Scale the image to the size we want
            AffineTransformOp scaleOp =
                    new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR); // use the bi linear transfomation mode
            scaled = scaleOp.filter(before, scaled); //Scale it
        }

        pixels = new int[width * height]; //Create a new 2d array of pixals
        for(int x = 0; x < scaled.getWidth(); x++){ //loop over each pixal column
            for(int y = 0; y < scaled.getHeight(); y++){ //loop over each pixel in column
                pixels[x + y*width] = roundColour(scaled.getRGB(x, y)); //round the colour and place it in the pixel array
            }
        }
    }

    public ImageData(byte[] bytes){
        pixels = new int[bytes.length / 2];
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        for(int i = 0; i < pixels.length; i++){
            pixels[i] = getRgb(bb.getShort(i*2));
        }
    }


    public byte[] getByteArray(){
        ByteBuffer bb = ByteBuffer.allocate(pixels.length * 2);
        for(int i = 0; i < pixels.length; i++){
            bb.putShort(getShortColour(pixels[i]));
        }
        return bb.array();
    }

    public static void main(String[] args){
        ImageData imageData = new ImageData(0x00112233,0x44556677, 0x8899AABB, 0xCCDDEEFF);
        System.out.println(getHexArray(imageData.getByteArray()));
    }


    public static final String getHexArray(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        return(sb.toString());
    }


    private int roundColour(int rgb){
        return (rgb & 0xF0F0F0F0) | 0x0F080808;
    }

    private short getShortColour(int rgb){
        return (short) (((rgb & 0xF0000000) >> 16) |
                ((rgb & 0x00F00000) >> 12) |
                ((rgb & 0x0000F000) >> 8) |
                ((rgb & 0x000000F0) >> 4));
    }

    private int getRgb(short rgb){
        return (
                ((rgb & 0xF000) << 16) |
                        ((rgb & 0x0F00) << 12) |
                        ((rgb & 0x00F0) << 8) |
                        ((rgb & 0x000F) << 4)) |
                0x0F080808;
    }

    public void setTexture(int[] pixalArray){
        for(int i = 0; i < pixalArray.length; i++){
            pixalArray[i] = pixels[i];
        }
    }
}
