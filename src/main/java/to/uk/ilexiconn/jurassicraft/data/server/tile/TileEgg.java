package to.uk.ilexiconn.jurassicraft.data.server.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import to.uk.ilexiconn.jurassicraft.JurassiCraft;
import to.uk.ilexiconn.jurassicraft.data.packet.PacketUpdateProgress;
import to.uk.ilexiconn.jurassicraft.data.server.entity.EntityEntry;

public class TileEgg extends TileEntity
{
    public int progress;
    public EntityEntry entityEntry;

    public TileEgg(EntityEntry entity)
    {
        entityEntry = entity;
    }

    public void writeToNBT(NBTTagCompound tag)
    {
        tag.setInteger("progress", progress);
    }

    public void readFromNBT(NBTTagCompound tag)
    {
        progress = tag.getInteger("progress");
        JurassiCraft.packetPipeline.sendToServer(new PacketUpdateProgress(worldObj, xCoord, yCoord, zCoord, tag.getInteger("progress")));
        System.out.println("now");
    }

    public void updateEntity()
    {
        progress++;
        if (!worldObj.isRemote) System.out.println(progress);
    }

    public Packet getDescriptionPacket()
    {
        System.out.println("nowq");
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }

    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet)
    {
        System.out.println("now2");
        readFromNBT(packet.func_148857_g());
    }
}
