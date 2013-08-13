package mods.custom_flags.client;

import cpw.mods.fml.common.IPlayerTracker;
import mods.custom_flags.CustomFlags;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

/**
 * User: nerd-boy
 * Date: 13/08/13
 * Time: 5:29 PM
 * TODO: Add discription
 */
public class CustomFlagsPlayerTracker implements IPlayerTracker{
    @Override
    public void onPlayerLogin(EntityPlayer player) {
        if(CustomFlags.DISPLAY_INSTRUCTIONS){
            player.sendChatToPlayer(ChatMessageComponent.func_111066_d(EnumChatFormatting.DARK_AQUA+StatCollector.translateToLocal("cf.welcome")));
        }
    }

    @Override
    public void onPlayerLogout(EntityPlayer player) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onPlayerChangedDimension(EntityPlayer player) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onPlayerRespawn(EntityPlayer player) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
