package to.uk.ilexiconn.jurassicraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import to.uk.ilexiconn.jurassicraft.data.packet.PacketPipeline;

@Mod(modid = "jurassicraft", name = "JurassiCraft", version = "b1.0.0")
public class JurassiCraft
{
    @Mod.Instance("jurassicraft")
    public static JurassiCraft instance;
    public static PacketPipeline packetPipeline;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) throws Exception
    {
        packetPipeline = new PacketPipeline();
        Util.getConfig().readConfig(event.getSuggestedConfigurationFile());
        Util.getProxy().init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        packetPipeline.init();
        Util.getAnimationAPI().init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        packetPipeline.postInit();
        Util.getAnimationAPI().postInit(event);
    }
}
