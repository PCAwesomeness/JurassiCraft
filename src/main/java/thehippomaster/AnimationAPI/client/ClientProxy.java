package thehippomaster.AnimationAPI.client;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Timer;
import thehippomaster.AnimationAPI.AnimationAPI;
import thehippomaster.AnimationAPI.CommonProxy;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{

    public void initTimer()
    {
        mcTimer = ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), AnimationAPI.fTimer);
    }

    public float getPartialTick()
    {
        return mcTimer.renderPartialTicks;
    }

    private Timer mcTimer;
}
