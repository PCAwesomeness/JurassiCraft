package to.uk.ilexiconn.jurassicraft.data.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.world.World;
import to.uk.ilexiconn.jurassicraft.data.server.tile.TileEgg;

public class PacketUpdateProgress extends AbstractPacket
{
    public int progress;

    public PacketUpdateProgress()
    {

    }

    public PacketUpdateProgress(World w, int x, int y, int z, int p)
    {
        setCoords(w, x, y, z);
        progress = p;
    }

    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
        buffer.writeInt(progress);
    }

    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
        progress = buffer.readInt();
    }

    public void handleClientSide()
    {

    }

    public void handleServerSide()
    {
        ((TileEgg) world.getTileEntity(xCoord, yCoord, zCoord)).progress = progress;
    }
}
