package to.uk.ilexiconn.jurassicraft.data.server.tile;

import buildcraft.api.transport.IPipeConnection;
import buildcraft.api.transport.IPipeTile;
import buildcraft.core.TileBuildCraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

public class TileCultivate extends TileBuildCraft implements IFluidHandler, IPipeConnection
{
    public int rotation;
    public int progress;
    public float animationTick;
    public int fluidAmount;

    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setInteger("rotation", rotation);
    }

    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        rotation = tag.getInteger("rotation");
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

    public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
    {
        fluidAmount = fluidAmount + resource.amount;
        return fluidAmount;
    }

    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
    {
        fluidAmount = fluidAmount - resource.amount;
        return new FluidStack(FluidRegistry.WATER, fluidAmount);
    }

    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
    {
        fluidAmount = fluidAmount - maxDrain;
        return new FluidStack(FluidRegistry.WATER, fluidAmount);
    }

    public boolean canFill(ForgeDirection from, Fluid fluid)
    {
        return true;
    }

    public boolean canDrain(ForgeDirection from, Fluid fluid)
    {
        return true;
    }

    public FluidTankInfo[] getTankInfo(ForgeDirection from)
    {
        return new FluidTankInfo[0];
    }

    public ConnectOverride overridePipeConnection(IPipeTile.PipeType type, ForgeDirection with)
    {
        return ConnectOverride.CONNECT;
    }
}
