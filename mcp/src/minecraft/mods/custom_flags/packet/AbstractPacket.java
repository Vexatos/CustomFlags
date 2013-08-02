package mods.custom_flags.packet;

import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

/**
 * Created by Aaron on 3/08/13.
 */
public abstract class AbstractPacket {

    public abstract void process(INetworkManager manager, Packet250CustomPayload packet, Player player);

}
