package thehippomaster.AnimationAPI;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import net.minecraft.entity.Entity;
import thehippomaster.AnimationAPI.packet.PacketAnim;
import thehippomaster.AnimationAPI.packet.PacketPipeline;
import to.uk.ilexiconn.jurassicraft.Util;

public class AnimationAPI
{
    public void init(FMLInitializationEvent e)
    {
        packetPipeline.initialize();
        packetPipeline.registerPacket(PacketAnim.class);
    }

    public void postInit(FMLPostInitializationEvent e)
    {
        Util.getAnimationProxy().initTimer();
        packetPipeline.postInitialize();
    }

    public static boolean isClient()
    {
        return FMLCommonHandler.instance().getSide().isClient();
    }

    public static boolean isEffectiveClient()
    {
        return FMLCommonHandler.instance().getEffectiveSide().isClient();
    }

    public static void sendAnimPacket(IAnimatedEntity entity, int animID)
    {
        if (isEffectiveClient()) return;
        entity.setAnimID(animID);
        Entity e = (Entity) entity;
        packetPipeline.sendToAll(new PacketAnim((byte) animID, e.getEntityId()));
    }

    public static final PacketPipeline packetPipeline = new PacketPipeline();

    public static final String[] fTimer;

    static
    {
        fTimer = new String[]{"field_71428_T", "S", "timer"};
    }
}
