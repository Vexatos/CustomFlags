package mods.custom_flags.packet;

import cpw.mods.fml.common.network.Player;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

/**
 * Created by Aaron on 3/08/13.
 */
public class FlagTileEntityDescripPacket extends AbstractPacket{

    public static final String channel = "CF|FlagTileDescrip";

    @Override
    public void process(INetworkManager manager, Packet250CustomPayload packet, Player player) {

    }
}
