package to.uk.ilexiconn.jurassicraft.data.client.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.FluidRegistry;
import to.uk.ilexiconn.jurassicraft.Util;

public class RenderCultivateWater implements ISimpleBlockRenderingHandler
{
    public int renderId = RenderingRegistry.getNextAvailableRenderId();

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
    {

    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        renderer.renderAllFaces = true;
        renderer.overrideBlockTexture = FluidRegistry.WATER.getStillIcon();

        renderer.setRenderBounds(0.05d, 0.37d, 0.05d, 0.95d, 1.1525d, 0.95d);
        if (world.getBlock(x, y, z) == Util.getBlock(2)) renderer.renderStandardBlock(Blocks.water, x, y, z);
        renderer.setRenderBounds(0.05d, 1.1525d, 0.05d, 0.95d, 1.935d, 0.95d);
        if (world.getBlock(x, y, z) == Util.getBlock(2)) renderer.renderStandardBlock(Blocks.water, x, y, z);

        return world.getBlock(x, y, z) == Util.getBlock(2);
    }

    public boolean shouldRender3DInInventory(int modelId)
    {
        return false;
    }

    public int getRenderId()
    {
        return renderId;
    }
}
