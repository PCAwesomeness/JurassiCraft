package to.uk.ilexiconn.jurassicraft.data.client.block;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import to.uk.ilexiconn.jurassicraft.api.Util;
import to.uk.ilexiconn.jurassicraft.data.client.block.model.ModelEgg;
import to.uk.ilexiconn.jurassicraft.data.server.tile.TileEgg;

public class RenderEggBlock extends TileEntitySpecialRenderer
{
    public ModelEgg model = new ModelEgg();
    public ResourceLocation texture;

    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float meta)
    {
        TileEgg tile = (TileEgg) tileEntity;
        if (texture == null) texture = new ResourceLocation(Util.getModId() + "textures/blocks/egg_" + tile.entityEntry.name.toLowerCase() + ".png");
        GL11.glPushMatrix();
        GL11.glColor4f(1f, 1f, 1f, 1f);
        GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
        GL11.glRotatef(180f, 0f, 0f, 1f);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        model.render();
        GL11.glPopMatrix();
    }
}
