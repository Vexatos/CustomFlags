package mods.custom_flags.packet;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import cpw.mods.fml.common.network.Player;
import mods.custom_flags.blocks.TileEntityFlagPole;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;

import java.io.*;

/**
 * Created by Aaron on 3/08/13.
 */
public class FlagTileEntityDescripPacket extends AbstractPacket{

    public static final String channel = "CF.FlagDes";

    public Packet250CustomPayload generatePacket(int x, int y, int z, ItemStack stack){

        DataOutputStream outputStream = new DataOutputStream(new ByteArrayOutputStream());

        try{
            outputStream.writeInt(x);
            outputStream.writeInt(y);
            outputStream.writeInt(z);

            Packet.writeItemStack(stack, outputStream);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new Packet250CustomPayload(channel, outputStream.get)

    }

    @Override
    public void process(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

        int x = 0;
        int y = 0;
        int z = 0;
        ItemStack stack = null;

        try{

            x = inputStream.readInt();
            y = inputStream.readInt();
            z = inputStream.readInt();

            stack = Packet.readItemStack(inputStream);

        }catch (Exception e){
            e.printStackTrace();
        }

        TileEntity te = ((EntityPlayer)player).worldObj.getBlockTileEntity(x,y,z);
        if(te != null && te instanceof TileEntityFlagPole){
            ((TileEntityFlagPole)te).setFlag(stack);
        }
    }
}
