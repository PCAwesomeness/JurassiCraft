package to.uk.ilexiconn.jurassicraft.data.client.block;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import org.lwjgl.opengl.GL11;
import to.uk.ilexiconn.jurassicraft.Util;
import to.uk.ilexiconn.jurassicraft.data.client.block.model.ModelCultivate;
import to.uk.ilexiconn.jurassicraft.data.client.block.model.ModelEmbryo;
import to.uk.ilexiconn.jurassicraft.data.server.block.BlockCultivate;
import to.uk.ilexiconn.jurassicraft.data.server.tile.TileCultivate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RenderCultivateBlock extends TileEntitySpecialRenderer
{
    public String[] colors = {"black", "red", "green", "brown", "blue", "purple", "cyan", "light_gray", "gray", "pink", "lime", "yellow", "light_blue", "magenta", "orange", "white"};
    public ModelCultivate cultivate = new ModelCultivate();
    public Map<Fluid, int[]> stillRenderCache = new HashMap<Fluid, int[]>();
    public RenderInfo liquidBlock = new RenderInfo();
    public RenderBlocks renderBlocks = new RenderBlocks();
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
        int[] displayLists = stillRenderCache.get(fluid);
        if (displayLists != null) return displayLists;

        displayLists = new int[100];

        liquidBlock.baseBlock = Blocks.water;
        liquidBlock.texture = FluidRegistry.WATER.getStillIcon();

        stillRenderCache.put(fluid, displayLists);

        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_CULL_FACE);

        for (int s = 0; s < 100; ++s)
        {
            displayLists[s] = GLAllocation.generateDisplayLists(1);
            GL11.glNewList(displayLists[s], 4864);

            liquidBlock.minX = 0.01f;
            liquidBlock.minY = 0;
            liquidBlock.minZ = 0.01f;

            liquidBlock.maxX = 0.99f;
            liquidBlock.maxY = (float) s / (float) 100;
            liquidBlock.maxZ = 0.99f;

            renderBlock(liquidBlock, world, 0, 0, 0, false);

            GL11.glEndList();
        }

        GL11.glColor4f(1, 1, 1, 1);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);

        return displayLists;
    }

    public static class RenderInfo
    {
        public double minX;
        public double minY;
        public double minZ;
        public double maxX;
        public double maxY;
        public double maxZ;
        public Block baseBlock = Blocks.sand;
        public IIcon texture = null;
        public IIcon[] textureArray = null;
        public boolean[] renderSide = new boolean[6];
        public float light = -1f;
        public int brightness = -1;

        public RenderInfo()
        {
            setRenderAllSides();
        }

        public RenderInfo(Block template, IIcon[] texture)
        {
            this();
            baseBlock = template;
            textureArray = texture;
        }

        public RenderInfo(float minX, float minY, float minZ, float maxX, float maxY, float maxZ)
        {
            this();
            setBounds(minX, minY, minZ, maxX, maxY, maxZ);
        }

        public final void setBounds(double minX, double minY, double minZ, double maxX, double maxY, double maxZ)
        {
            this.minX = minX;
            this.minY = minY;
            this.minZ = minZ;
            this.maxX = maxX;
            this.maxY = maxY;
            this.maxZ = maxZ;
        }

        public final void setRenderAllSides()
        {
            Arrays.fill(renderSide, true);
        }

        public IIcon getBlockTextureFromSide(int i)
        {
            if (texture != null) return texture;

            int index = i;

            if (textureArray == null || textureArray.length == 0) return baseBlock.getBlockTextureFromSide(index);
            else if (index >= textureArray.length) index = 0;
            return textureArray[index];
        }
    }

    public void renderBlock(RenderInfo info, IBlockAccess blockAccess, int x, int y, int z, boolean doLight)
    {
        float lightBottom = 0.5F;
        float lightTop = 1.0F;
        float lightEastWest = 0.8F;
        float lightNorthSouth = 0.6F;

        Tessellator tessellator = Tessellator.instance;

        boolean realDoLight = doLight;

        if (blockAccess == null) realDoLight = false;

        tessellator.startDrawingQuads();

        float light = 0;
        if (realDoLight)
        {
            if (info.light < 0)
            {
                light = info.baseBlock.getMixedBrightnessForBlock(blockAccess, x, y, z);
                light = light + ((1.0f - light) * 0.4f);
            }
            else light = info.light;
            int brightness;
            if (info.brightness < 0) brightness = info.baseBlock.getMixedBrightnessForBlock(blockAccess, x, y, z);
            else brightness = info.brightness;
            tessellator.setBrightness(brightness);
            tessellator.setColorOpaque_F(lightBottom * light, lightBottom * light, lightBottom * light);
        }
        else if (info.brightness >= 0) tessellator.setBrightness(info.brightness);

        renderBlocks.setRenderBounds(info.minX, info.minY, info.minZ, info.maxX, info.maxY, info.maxZ);

        if (info.renderSide[0]) renderBlocks.renderFaceYNeg(info.baseBlock, x, y, z, info.getBlockTextureFromSide(0));

        if (realDoLight) tessellator.setColorOpaque_F(lightTop * light, lightTop * light, lightTop * light);

        if (info.renderSide[1]) renderBlocks.renderFaceYPos(info.baseBlock, x, y, z, info.getBlockTextureFromSide(1));

        if (realDoLight) tessellator.setColorOpaque_F(lightEastWest * light, lightEastWest * light, lightEastWest * light);

        if (info.renderSide[2]) renderBlocks.renderFaceZNeg(info.baseBlock, x, y, z, info.getBlockTextureFromSide(2));

        if (realDoLight) tessellator.setColorOpaque_F(lightEastWest * light, lightEastWest * light, lightEastWest * light);

        if (info.renderSide[3]) renderBlocks.renderFaceZPos(info.baseBlock, x, y, z, info.getBlockTextureFromSide(3));

        if (realDoLight) tessellator.setColorOpaque_F(lightNorthSouth * light, lightNorthSouth * light, lightNorthSouth * light);

        if (info.renderSide[4]) renderBlocks.renderFaceXNeg(info.baseBlock, x, y, z, info.getBlockTextureFromSide(4));

        if (realDoLight) tessellator.setColorOpaque_F(lightNorthSouth * light, lightNorthSouth * light, lightNorthSouth * light);

        if (info.renderSide[5]) renderBlocks.renderFaceXPos(info.baseBlock, x, y, z, info.getBlockTextureFromSide(5));

        tessellator.draw();
    }
}
