package mods.custom_flags.client;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import mods.custom_flags.items.ItemFlag;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.EnumSet;

/**
 * Created by Aaron on 4/08/13.
 */
public class MapTextureReplacer implements ITickHandler {

    private boolean lastFlag = true;
    private boolean needsChange = true;

    private ResourceLocation blank = new ResourceLocation("cusatom_flags:blank");
    private ResourceLocation defaultMap;

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {

    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {

        try{
            if(defaultMap == null){
                Field mapTex = ItemRenderer.class.getDeclaredFields()[1];
                mapTex.setAccessible(true);
                defaultMap = (ResourceLocation) mapTex.get(null);
            }


            EntityPlayer player = (EntityPlayer) tickData[0];

            ItemStack current = player.getCurrentEquippedItem();

            if(current.getItem() instanceof ItemFlag){
                setFinalStatic(ItemRenderer.class, 1, blank);
            }else{
                setFinalStatic(ItemRenderer.class, 1, defaultMap);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }


    static void setFinalStatic(Class clazz, int filedIndex, Object newValue) throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredFields()[filedIndex];
        field.setAccessible(true);
        Field modifiers = field.getClass().getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, newValue);
    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.PLAYER);
    }

    @Override
    public String getLabel() {
        return null;
    }
}
