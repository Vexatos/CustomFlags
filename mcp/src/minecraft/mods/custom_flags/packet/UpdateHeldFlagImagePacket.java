package mods.custom_flags.packet;

import cpw.mods.fml.common.network.Player;
import mods.custom_flags.blocks.TileEntityFlagPole;
import mods.custom_flags.items.ItemFlag;
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

/**
 * Created by Aaron on 3/08/13.
 */
public class UpdateHeldFlagImagePacket extends AbstractPacket {

    public static final String channel = "CF.UpdateFlag";

    public static Packet250CustomPayload generatePacket(String user, ItemStack stack){

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream outputStream = new DataOutputStream(bos);

        try{
            outputStream.writeUTF(user);

            Packet.writeItemStack(stack, outputStream);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new Packet250CustomPayload(channel, bos.toByteArray());

    }

    @Override
    public void process(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

        String user = null;
        ItemStack stack = null;

        try{

           user = inputStream.readUTF();
           stack = Packet.readItemStack(inputStream);

        }catch (Exception e){
            e.printStackTrace();
        }

        if(user != null){
            EntityPlayer target = ((EntityPlayer)player).worldObj.getPlayerEntityByName(user);
            if(target != null){
                ItemStack targetEquip = target.getCurrentEquippedItem();
                if(targetEquip != null && targetEquip.getItem() instanceof ItemFlag){
                    target.setCurrentItemOrArmor(0, stack);
                }
            }

        }
    }
}
