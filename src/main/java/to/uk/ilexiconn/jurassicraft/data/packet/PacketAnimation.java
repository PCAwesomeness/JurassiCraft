package to.uk.ilexiconn.jurassicraft.data.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.Minecraft;
import to.uk.ilexiconn.jurassicraft.api.IAnimatedEntity;

public class PacketAnimation extends AbstractPacket
{
    public byte animationId;
    public int entityId;

    public PacketAnimation()
    {

    }

    public PacketAnimation(byte anim, int entity)
    {
        animationId = anim;
        entityId = entity;
    }

    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
        buffer.writeByte(animationId);
        buffer.writeInt(entityId);
    }

    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
        animationId = buffer.readByte();
        entityId = buffer.readInt();
    }

    public void handleClientSide()
    {
        IAnimatedEntity entity = (IAnimatedEntity) Minecraft.getMinecraft().theWorld.getEntityByID(entityId);
        if (entity != null && animationId != -1)
        {
            entity.setAnimID(animationId);
            if (animationId == 0) entity.setAnimTick(0);
        }
    }

    public void handleServerSide()
    {

    }
}
