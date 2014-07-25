package to.uk.ilexiconn.jurassicraft.data.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Timer;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import to.uk.ilexiconn.jurassicraft.JurassiCraft;
import to.uk.ilexiconn.jurassicraft.api.Util;
import to.uk.ilexiconn.jurassicraft.data.server.ServerProxy;

public class ClientProxy extends ServerProxy
{
    public Timer mcTimer;

    public void init() throws Exception
    {
        super.init();
        mcTimer = ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), JurassiCraft.fTimer);
        Util.getData().initClient();
        Util.getEntityParser().initClient();
    }

    public float getPartialTick()
    {
        return mcTimer.renderPartialTicks;
    }

    public void renderEntity(Class<? extends EntityLiving> entity, RenderLiving renderLiving)
    {
        RenderingRegistry.registerEntityRenderingHandler(entity, renderLiving);
    }

    public void renderBlock(Class<? extends TileEntity> tileEntity, TileEntitySpecialRenderer renderer)
    {
        ClientRegistry.bindTileEntitySpecialRenderer(tileEntity, renderer);
    }

    public void renderItem(Item item, IItemRenderer render)
    {
        MinecraftForgeClient.registerItemRenderer(item, render);
    }
}
