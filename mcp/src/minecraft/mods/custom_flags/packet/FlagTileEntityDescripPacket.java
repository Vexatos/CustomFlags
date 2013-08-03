package mods.custom_flags.packet;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/**
 * Created by Aaron on 3/08/13.
 */
public class FlagTileEntityDescripPacket extends AbstractPacket{

    public static final String channel = "CF|FlagTileDescrip";

    @Override
    public void process(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

        int x = 0;
        int y = 0;
        int z = 0;
        ItemStack stack;

        try{

            x = inputStream.readInt();
            y = inputStream.readInt();
            z = inputStream.readInt();

            stack = Packet.readItemStack(inputStream);

        }catch (Exception e){
            e.printStackTrace();
        }

        ((EntityPlayer)player).worldObj.getBlockTileEntity(x,y,z);

    }
}
