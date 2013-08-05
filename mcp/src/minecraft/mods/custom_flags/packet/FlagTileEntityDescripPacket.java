package mods.custom_flags.packet;

import cpw.mods.fml.common.network.Player;
import mods.custom_flags.blocks.TileEntityFlagPole;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron on 3/08/13.
 */
public class FlagTileEntityDescripPacket extends AbstractPacket{

    public static final String channel = "CF.FlagDes";

    public static Packet250CustomPayload generatePacket(int x, int y, int z, List<ItemStack> flags){

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream outputStream = new DataOutputStream(bos);

        try{
            outputStream.writeInt(x);
            outputStream.writeInt(y);
            outputStream.writeInt(z);

            outputStream.writeByte(((byte) flags.size()));

            for(ItemStack f:flags){
                Packet.writeItemStack(f, outputStream);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return new Packet250CustomPayload(channel, bos.toByteArray());

    }

    @Override
    public void process(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

        int x = 0;
        int y = 0;
        int z = 0;
        int size = 0;
        List<ItemStack> flags = new ArrayList<ItemStack>();

        try{

            x = inputStream.readInt();
            y = inputStream.readInt();
            z = inputStream.readInt();
            size = inputStream.readByte();

            for(int i = 0; i < size; i++){
                flags.add(Packet.readItemStack(inputStream));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        TileEntity te = ((EntityPlayer)player).worldObj.getBlockTileEntity(x,y,z);
        if(te != null && te instanceof TileEntityFlagPole){
            ((TileEntityFlagPole) te).clearFlags();
            for(ItemStack flag:flags){
                ((TileEntityFlagPole)te).setFlag(flag);
            }
        }
    }
}
