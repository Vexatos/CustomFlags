package mods.custom_flags.client.gui.controls.canvus_tools;

import mods.custom_flags.utils.ImageData;
import net.minecraft.util.ResourceLocation;

/**
 * User: nerd-boy
 * Date: 12/08/13
 * Time: 12:04 PM
 * TODO: Add discription
 */
public class CircleTool extends RectangleTool {

    @Override
    public String getToolName() {
        return "tool.circle";
    }

    private void plotPoint(int x, int y, int[] pixels, int rgb){
        if (x > -1 &&  x < ImageData.IMAGE_RES && y > -1 && y < ImageData.IMAGE_RES){
            pixels[x+ImageData.IMAGE_RES*y] = rgb;
        }
    }

    //@Override
    protected void drawShape2(int minX, int minY, int maxX, int maxY, int[] pixels, int rgb) {
        //Formula = (x-h)^2/a^2 +(y-k)^2/b^2 = 1


        float x_mid = (maxX + minX) / 2F;
        float y_mid = (maxY + minY) / 2F;

        float x_rad = (maxX - minX) / 2F;
        float y_rad = (maxY - minY) / 2F;

        float x_rad_sq = x_rad * x_rad;
        float y_rad_sq = y_rad * y_rad;

        for(float time = 0; time < Math.PI/2; time += 0.05F){
            plotPoint((int)Math.round((x_mid + x_rad * Math.cos(time))), (int)Math.round(y_mid + y_rad * Math.sin(time)), pixels, rgb);
            plotPoint((int)Math.round((x_mid - x_rad * Math.cos(time))), (int)Math.round(y_mid + y_rad * Math.sin(time)), pixels, rgb);
            plotPoint((int)Math.round((x_mid + x_rad * Math.cos(time))), (int)Math.round(y_mid - y_rad * Math.sin(time)), pixels, rgb);
            plotPoint((int)Math.round((x_mid - x_rad * Math.cos(time))), (int)Math.round(y_mid - y_rad * Math.sin(time)), pixels, rgb);
        }





    }

    @Override
    protected void drawShape(int minX, int minY, int maxX, int maxY, int[] pixels, int rgb) {
        float x_mid = (maxX + minX) / 2F;
        float y_mid = (maxY + minY) / 2F;

        float x_rad = (maxX - minX) / 2F;
        float y_rad = (maxY - minY) / 2F;

        float x_r_sq = x_rad * x_rad;
        float y_r_sq = y_rad * y_rad;

        int last_x = 0;
        float last_y = y_rad;

        for(int x = 1; x <= x_rad; x++){
            float y = (float) (y_rad * Math.sqrt(x_r_sq - (x * x)) / x_rad);

            drawLine((int)Math.ceil(x_mid+last_x), (int)Math.ceil(x_mid+x), (int)Math.ceil(y_mid+last_y), (int)Math.ceil(y_mid+y), pixels, rgb);
            drawLine((int)Math.floor(x_mid-last_x), (int)Math.floor(x_mid-x), (int)Math.ceil(y_mid+last_y), (int)Math.ceil(y_mid+y), pixels, rgb);

            drawLine((int)Math.ceil(x_mid+last_x), (int)Math.ceil(x_mid+x), (int)Math.floor(y_mid-last_y), (int)Math.floor(y_mid-y), pixels, rgb);
            drawLine((int)Math.floor(x_mid-last_x), (int)Math.floor(x_mid-x), (int)Math.floor(y_mid-last_y), (int)Math.floor(y_mid-y), pixels, rgb);


            last_x = x;
            last_y = y;

        }


        drawLine((int)Math.floor(x_mid-last_x), (int)Math.floor(x_mid-last_x), (int)Math.floor(y_mid-last_y), (int)Math.ceil(y_mid+last_y), pixels, rgb);
        drawLine((int)Math.ceil(x_mid+last_x), (int)Math.ceil(x_mid+last_x), (int)Math.floor(y_mid-last_y), (int)Math.ceil(y_mid+last_y), pixels, rgb);


        //drawLine(Math.round(x_mid+last_x), Math.round(x_mid+last_x), Math.round(y_mid-last_y), Math.round(y_mid+last_y), pixels, rgb);

    }



    /*
    @Override
    protected void drawShape(int minX, int minY, int maxX, int maxY, int[] pixels, int rgb) {
        //Formula = (x-h)^2/a^2 +(y-k)^2/b^2 = 1

        float x_mid = (maxX + minX) / 2F;
        float y_mid = (maxY + minY) / 2F;

        float x_rad = (maxX - minX) / 2F;
        float y_rad = (maxY - minY) / 2F;

        float x_rad_sq = x_rad * x_rad;
        float y_rad_sq = y_rad * y_rad;

        float y = y_rad;
        float errorThreshold = 1F;

        for(float x = 0; x <= x_rad/2+1; x++){
            float error = (x*x / x_rad_sq) + (y*y / y_rad_sq);

            if(error < errorThreshold){
                plotPoint(Math.round(x+x_mid),Math.round(y_mid+y), pixels, rgb);
                plotPoint(Math.round(x_mid- x),Math.round(y_mid+y), pixels, rgb);
                plotPoint(Math.round(x+x_mid),Math.round(y_mid-y), pixels, rgb);
                plotPoint(Math.round(x_mid- x),Math.round(y_mid-y), pixels, rgb);
            }else{
                y--;
                plotPoint(Math.round(x+x_mid),Math.round(y_mid+y), pixels, rgb);
                plotPoint(Math.round(x_mid- x),Math.round(y_mid+y), pixels, rgb);
                plotPoint(Math.round(x+x_mid),Math.round(y_mid-y), pixels, rgb);
                plotPoint(Math.round(x_mid- x),Math.round(y_mid-y), pixels, rgb);
            }
        }

        float x=x_rad;
        for(y = 0; y <= y_rad/2; y++){
            float error = (x*x / x_rad_sq) + (y*y / y_rad_sq);

            if(error < errorThreshold){
                plotPoint(Math.round(x+x_mid),Math.round(y_mid+y), pixels, rgb);
                plotPoint(Math.round(x_mid- x),Math.round(y_mid+y), pixels, rgb);
                plotPoint(Math.round(x+x_mid),Math.round(y_mid-y), pixels, rgb);
                plotPoint(Math.round(x_mid- x),Math.round(y_mid-y), pixels, rgb);
            }else{
                x--;
                plotPoint(Math.round(x+x_mid),Math.round(y_mid+y), pixels, rgb);
                plotPoint(Math.round(x_mid- x),Math.round(y_mid+y), pixels, rgb);
                plotPoint(Math.round(x+x_mid),Math.round(y_mid-y), pixels, rgb);
                plotPoint(Math.round(x_mid- x),Math.round(y_mid-y), pixels, rgb);
            }
        }
/*
        float x=x_rad;
        for(y = 0; y <= y_rad; y++){
            plotPoint(Math.round(x+x_mid),Math.round(y_mid+y), pixels, rgb);
            plotPoint(Math.round(x_mid- x),Math.round(y_mid+y), pixels, rgb);
            plotPoint(Math.round(x+x_mid),Math.round(y_mid-y), pixels, rgb);
            plotPoint(Math.round(x_mid- x),Math.round(y_mid-y), pixels, rgb);
            float error = Math.abs(x*x / x_rad_sq + y*y / y_rad_sq);

            if(error > errorThreshold){
                x--;
            }
        }*/

    //}

    /*
    @Override
    protected void drawShape(int minX, int minY, int maxX, int maxY, int[] pixels, int rgb) {

        int r_x = (maxX - minX);
        int r_y = (maxY - minY);

        int mid_x = (maxX + minX);
        int mid_y = (maxY + minY);

        int rSq_x = r_x * r_x;
        int rSq_y = r_y * r_y;

        int x = 0;
        int y = r_y;

        int p_x = 0;
        int p_y = 2 * rSq_x * y;


        plotPoint(Math.round((mid_x+x)/2F), Math.round((mid_y+y)/2F), pixels, rgb);
        plotPoint(Math.round((mid_x-x)/2F), Math.round((mid_y+y)/2F), pixels, rgb);
        plotPoint(Math.round((mid_x-x)/2F), Math.round((mid_y-y)/2F), pixels, rgb);
        plotPoint(Math.round((mid_x+x)/2F), Math.round((mid_y-y)/2F), pixels, rgb);

        float p = (rSq_y - (rSq_x * r_y) + 0.25F*rSq_x);

        while(p_x < p_y){
            x ++;
            p_x=p_x+2*rSq_y;
            if(p < 0){
                p = p +rSq_y+p_x;
            }else{
                y--;
                p_y=p_y-2*rSq_x;
                p = p +rSq_y + p_x-p_y;
            }
            plotPoint(Math.round((mid_x+x)/2F), Math.round((mid_y+y)/2F), pixels, rgb);
            plotPoint(Math.round((mid_x-x)/2F), Math.round((mid_y+y)/2F), pixels, rgb);
            plotPoint(Math.round((mid_x-x)/2F), Math.round((mid_y-y)/2F), pixels, rgb);
            plotPoint(Math.round((mid_x+x)/2F), Math.round((mid_y-y)/2F), pixels, rgb);
        }



    }




    /*

    int hh = height * height;
int ww = width * width;
int hhww = hh * ww;
int x0 = width;
int dx = 0;

// do the horizontal diameter
for (int x = -width; x <= width; x++)
    setpixel(origin.x + x, origin.y);

// now do both halves at the same time, away from the diameter
for (int y = 1; y <= height; y++)
{
    int x1 = x0 - (dx - 1);  // try slopes of dx - 1 or more
    for ( ; x1 > 0; x1--)
        if (x1*x1*hh + y*y*ww <= hhww)
            break;
    dx = x0 - x1;  // current approximation of the slope
    x0 = x1;

    for (int x = -x0; x <= x0; x++)
    {
        setpixel(origin.x + x, origin.y - y);
        setpixel(origin.x + x, origin.y + y);
    }
}


     */

    /*
    void DrawEllipse (int x0, int y0, int width, int height)
{
    int a2 = width * width;
    int b2 = height * height;
    int fa2 = 4 * a2, fb2 = 4 * b2;
    int x0, y0, x, y, sigma;

    first half
    for (x = 0, y = height, sigma = 2*b2+a2*(1-2*height); b2*x <= a2*y; x++)
    {
        DrawPixel (x0 + x, y0 + y);
        DrawPixel (x0 - x, y0 + y);
        DrawPixel (x0 + x, y0 - y);
        DrawPixel (x0 - x, y0 - y);
        if (sigma >= 0)
        {
            sigma += fa2 * (1 - y);
            y--;
        }
        sigma += b2 * ((4 * x) + 6);
    }

    second half
    for (x = width, y = 0, sigma = 2*a2+b2*(1-2*width); a2*y <= b2*x; y++)
    {
        DrawPixel (x0 + x, y0 + y);
        DrawPixel (x0 - x, y0 + y);
        DrawPixel (x0 + x, y0 - y);
        DrawPixel (x0 - x, y0 - y);
        if (sigma >= 0)
        {
            sigma += fb2 * (1 - x);
            x--;
        }
        sigma += a2 * ((4 * y) + 6);
    }
}
     */


    /*
    @Override
    protected void drawShape(int minX, int minY, int maxX, int maxY, int[] pixels, int rgb) {
        int width = (maxX - minX) / 2;
        int height = (maxY - minY)/ 2;

        int midX = (maxX + minX) / 2;
        int midY = (maxY + minY)/ 2;


        int h_2 = height * height;
        int w_2 = width * width;

        int prevSR = 0;

        for(int i = 1; i <= 2 * width; i++){

            int sqrRoot = (int) Math.sqrt(h_2 - (((h_2 * (width - i)) * (width - i)) / w_2));

            drawLine(minX + i-1, minX + i, minY + height+prevSR, minY + height+sqrRoot, pixels, rgb);

            drawLine(minX + i-1, minX + i, minY + height-prevSR, minY + height-sqrRoot, pixels, rgb);

            prevSR = sqrRoot;
        }
    }
    */




    private void drawLine(int x0, int x1, int y0, int y1, int[] pixelsCurrent, int rgb) {

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0<x1 ? 1 : -1;
        int sy = y0<y1 ? 1 : -1;
        int err = dx - dy;


        boolean done = false;
        while(!done){
            if (x0 > -1 &&  x0 < ImageData.IMAGE_RES && y0 > -1 && y0 < ImageData.IMAGE_RES){
                pixelsCurrent[x0+ImageData.IMAGE_RES*y0] = rgb;
            }
            if(x0 == x1 && y0 == y1){
                done = true;
            }
            int e2 = 2*err;
            if(e2 > -dy && !done){
                err = err - dy;
                x0 = x0 + sx;
            }
            if(x0 == x1 && y0 == y1 && !done){
                if (x0 > -1 &&  x0 < ImageData.IMAGE_RES && y0 > -1 && y0 < ImageData.IMAGE_RES){
                    pixelsCurrent[x0+ImageData.IMAGE_RES*y0] = rgb;
                }
                done = true;
            }
            if(e2 < dx && !done){
                err = err + dx;
                y0 = y0 + sy;
            }
        }
    }
}
