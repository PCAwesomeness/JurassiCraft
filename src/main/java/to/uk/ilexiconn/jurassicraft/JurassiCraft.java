package to.uk.ilexiconn.jurassicraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "jurassicraft", name = "JurassiCraft", version = "b1.0.0")
public class JurassiCraft
{
    @Mod.Instance("jurassicraft")
    public static JurassiCraft instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) throws Exception
    {
        Util.getProxy().init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        Util.getAnimationAPI().init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        Util.getAnimationAPI().postInit(event);
    }
}
