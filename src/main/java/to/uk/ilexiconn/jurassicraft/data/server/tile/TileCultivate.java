package to.uk.ilexiconn.jurassicraft.data.server.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileCultivate extends TileEntity
{
    public int rotation;
    public int progress;
    public float animationTick;
    public int fluidLevel;

    public void writeToNBT(NBTTagCompound tag)
    {
        tag.setInteger("rotation", rotation);
        tag.setInteger("level", fluidLevel);
    }

    public void readFromNBT(NBTTagCompound tag)
    {
        rotation = tag.getInteger("rotation");
        fluidLevel = tag.getInteger("level");
    }

    public Packet getDescriptionPacket()
    {
        super.getDescriptionPacket();
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }

    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet)
    {
        super.onDataPacket(networkManager, packet);
        readFromNBT(packet.func_148857_g());
    }

    public void updateEntity()
    {
        animationTick += 0.3f;
    }
}
