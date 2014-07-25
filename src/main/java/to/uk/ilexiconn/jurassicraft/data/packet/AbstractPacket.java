package to.uk.ilexiconn.jurassicraft.data.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.world.World;

public abstract class AbstractPacket
{
    public int xCoord, yCoord, zCoord;
    public World world;

    public abstract void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer);

    public abstract void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer);

    public abstract void handleClientSide();

    public abstract void handleServerSide();

    public void setCoords(World w, int x, int y, int z)
    {
        world = w;
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }
}