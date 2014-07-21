package to.uk.ilexiconn.jurassicraft.data.client.block;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import org.lwjgl.opengl.GL11;
import to.uk.ilexiconn.jurassicraft.Util;
import to.uk.ilexiconn.jurassicraft.data.client.block.model.ModelCultivate;
import to.uk.ilexiconn.jurassicraft.data.client.block.model.ModelEmbryo;
import to.uk.ilexiconn.jurassicraft.data.server.block.BlockCultivate;
import to.uk.ilexiconn.jurassicraft.data.server.tile.TileCultivate;

import java.util.HashMap;
import java.util.Map;

public class RenderCultivateBlock extends TileEntitySpecialRenderer
{
    public String[] colors = {"black", "red", "green", "brown", "blue", "purple", "cyan", "light_gray", "gray", "pink", "lime", "yellow", "light_blue", "magenta", "orange", "white"};
    public ModelCultivate cultivate = new ModelCultivate();
    public Map<Fluid, int[]> stillRenderCache = new HashMap<Fluid, int[]>();
    public RenderEntityBlock.RenderInfo liquidBlock = new RenderEntityBlock.RenderInfo();
    public ModelEmbryo embryo = new ModelEmbryo();
    public ResourceLocation[] cultivateTextures;
    public ResourceLocation embryoTextures;

    public RenderCultivateBlock()
    {
        cultivateTextures = new ResourceLocation[colors.length];
        for (int i = 0; i < colors.length; i++) cultivateTextures[i] = new ResourceLocation(Util.getModId() + "textures/blocks/cultivate_" + colors[i] + ".png");
        embryoTextures = new ResourceLocation(Util.getModId() + "textures/blocks/embryo.png");
    }

    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float meta)
    {
        GL11.glEnable(GL11.GL_BLEND);
        TileCultivate tile = (TileCultivate) tileEntity;

        if (tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord) == Util.getBlock(2))
        {
            GL11.glPushMatrix();
            GL11.glColor4f(1f, 1f, 1f, 1f);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
            GL11.glScalef(1f + (tile.progress / 10f + 0.1f), 1f + (tile.progress / 10f + 0.1f), 1f + (tile.progress / 10f + 0.1f));
            int rotation = BlockCultivate.getRotation(tile.getWorldObj(), tile.xCoord, tile.yCoord, tile.zCoord);
            GL11.glRotatef(rotation == 0 ? 0f : rotation == 1 ? -90f : rotation == 2 ? -180f : 90f, 0f, 1f, 0f);
            GL11.glRotatef(180f, 0f, 0f, 1f);
            Minecraft.getMinecraft().renderEngine.bindTexture(embryoTextures);
            embryo.render(tile);
            GL11.glPopMatrix();
        }

        GL11.glPushMatrix();
        GL11.glColor4f(1f, 1f, 1f, 1f);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
        GL11.glRotatef(180f, 0f, 0f, 1f);
        Minecraft.getMinecraft().renderEngine.bindTexture(cultivateTextures[tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord)]);
        cultivate.render(false);
        GL11.glPopMatrix();

        int[] displayList = getFluidDisplayLists(tile.getWorldObj());
        if (displayList != null)
        {
            GL11.glPushMatrix();
            GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            bindTexture(TextureMap.locationBlocksTexture);
            GL11.glTranslatef((float) x + 0.125f, (float) y + 1.8f, (float) z + 0.125f);
            GL11.glScalef(0.75f, 2.9f, 0.75f);
            GL11.glTranslatef(0, -0.5f, 0);
            GL11.glCallList(displayList[tile.fluidLevel * 2]);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }

        GL11.glPushMatrix();
        GL11.glColor4f(1f, 1f, 1f, 0.7f);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
        GL11.glRotatef(180f, 0f, 0f, 1f);
        Minecraft.getMinecraft().renderEngine.bindTexture(cultivateTextures[tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord)]);
        cultivate.renderGlass();
        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_BLEND);
    }

    public int[] getFluidDisplayLists(World world)
    {
        Fluid fluid = FluidRegistry.WATER;
        if (fluid == null) return null;
        int[] diplayLists = stillRenderCache.get(fluid);
        if (diplayLists != null) return diplayLists;

        diplayLists = new int[100];

        liquidBlock.baseBlock = Blocks.water;
        liquidBlock.texture = FluidRegistry.WATER.getStillIcon();

        stillRenderCache.put(fluid, diplayLists);

        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_CULL_FACE);

        for (int s = 0; s < 100; ++s)
        {
            diplayLists[s] = GLAllocation.generateDisplayLists(1);
            GL11.glNewList(diplayLists[s], 4864);

            liquidBlock.minX = 0.01f;
            liquidBlock.minY = 0;
            liquidBlock.minZ = 0.01f;

            liquidBlock.maxX = 0.99f;
            liquidBlock.maxY = (float) s / (float) 100;
            liquidBlock.maxZ = 0.99f;

            RenderEntityBlock.instance.renderBlock(liquidBlock, world, 0, 0, 0, false);

            GL11.glEndList();
        }

        GL11.glColor4f(1, 1, 1, 1);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);

        return diplayLists;
    }
}
