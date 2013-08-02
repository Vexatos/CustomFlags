package mods.custom_flags.client.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import mods.custom_flags.CustomFlags;
import mods.custom_flags.items.ItemFlag;
import mods.custom_flags.utils.ImageData;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * User: nerd-boy
 * Date: 2/08/13
 * Time: 3:02 PM
 * TODO: Add discription
 */
public class ImageCahce {

    private static ItemStack temp;

    private static final DynamicTexture defaultTexture;

    static{
        defaultTexture = new DynamicTexture(ImageData.IMAGE_RES, ImageData.IMAGE_RES);

        ImageData.defaultImage.setTexture(defaultTexture.func_110565_c());
    }

    private static final LoadingCache<String, DynamicTexture> imageCahce =
            CacheBuilder.newBuilder().maximumSize(CustomFlags.CAHCE_SIZE).
                    build(
                            new CacheLoader<String, DynamicTexture>() {
                                @Override
                                public DynamicTexture load(String key) throws Exception {
                                    DynamicTexture texture = new DynamicTexture(ImageData.IMAGE_RES, ImageData.IMAGE_RES);

                                    if (temp != null &&
                                            temp.getItem() instanceof ItemFlag &&
                                            ((ItemFlag) temp.getItem()).hasImageData(temp)) {


                                        new ImageData(((ItemFlag) temp.getItem()).getImageData(temp)).setTexture(texture.func_110565_c());

                                    }

                                    return texture;
                                }

                            }
                    );
    {
        CacheBuilder.newBuilder().build();
    }


    public static void setTexture(ItemStack stack){

        try{
            if (stack != null &&
                    stack.getItem() instanceof ItemFlag &&
                    ((ItemFlag) stack.getItem()).hasImageData(stack)) {
                temp = stack;
                imageCahce.get(((ItemFlag)stack.getItem()).getKey(stack)).func_110564_a();

            }else{
                ImageData.defaultImage.setTexture(defaultTexture.func_110565_c());
                defaultTexture.func_110564_a();
            }
        }catch(Exception e){
            ImageData.defaultImage.setTexture(defaultTexture.func_110565_c());
            defaultTexture.func_110564_a();
        }

    }




}
