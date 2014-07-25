package to.uk.ilexiconn.jurassicraft.data.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public abstract class AbstractPacket
{
    public abstract void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer);

    public abstract void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer);

    public abstract void handleClientSide();

    public abstract void handleServerSide();
}