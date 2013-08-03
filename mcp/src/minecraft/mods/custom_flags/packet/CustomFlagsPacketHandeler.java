package mods.custom_flags.packet;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import java.util.HashMap;

public class CustomFlagsPacketHandeler implements IPacketHandler{

    public static final CustomFlagsPacketHandeler INSTANCE = new CustomFlagsPacketHandeler();

    private HashMap<String, AbstractPacket> packetMap = new HashMap<String, AbstractPacket>();

    public CustomFlagsPacketHandeler(){
        packetMap.put(FlagTileEntityDescripPacket.channel, new FlagTileEntityDescripPacket());
        packetMap.put(UpdateHeldFlagImagePacket.channel, new UpdateHeldFlagImagePacket());
    }

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        AbstractPacket p = packetMap.get(packet.channel);
        if(p!=null){
            p.process(manager, packet, player) ;
        }
    }
}
