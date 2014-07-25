package to.uk.ilexiconn.jurassicraft.data.server;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import to.uk.ilexiconn.jurassicraft.Util;

public class ServerProxy
{
    public void init() throws Exception
    {
        Util.getData().init();
        Util.getEntityParser().init();
    }

    public float getPartialTick()
    {
        return 1f;
    }

    public void renderEntity(Class<? extends EntityLiving> entity, RenderLiving renderLiving)
    {

    }

    public void renderBlock(Class<? extends TileEntity> tileEntity, TileEntitySpecialRenderer renderer)
    {

    }

    public void renderItem(Item item, IItemRenderer render)
    {

    }
}
