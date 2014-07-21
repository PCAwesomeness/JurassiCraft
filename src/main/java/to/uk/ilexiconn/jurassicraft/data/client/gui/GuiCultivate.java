package to.uk.ilexiconn.jurassicraft.data.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import to.uk.ilexiconn.jurassicraft.Util;
import to.uk.ilexiconn.jurassicraft.data.server.gui.container.ContainerCultivate;
import to.uk.ilexiconn.jurassicraft.data.server.tile.TileCultivate;

public class GuiCultivate extends GuiContainer
{
    public ResourceLocation texture = new ResourceLocation(Util.getModId() + "textures/gui/GuiCultivate.png");
    public TileCultivate tileCultivate;

    public GuiCultivate(InventoryPlayer inventoryPlayer, TileCultivate tile)
    {
        super(new ContainerCultivate(inventoryPlayer, tile));
        tileCultivate = tile;
    }

    public void drawGuiContainerForegroundLayer(int x, int y)
    {
        fontRendererObj.drawString("Cultivate", 8, 8, 0x4bc2fc);
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 95, 0x4bc2fc);
    }

    public void drawGuiContainerBackgroundLayer(float meta, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect((width - xSize) / 2, (height - ySize) / 2, 0, 0, xSize, ySize);
        drawTexturedModalRect((width - xSize) / 2 + 79, (height - ySize) / 2 + 41, 177, 0, tileCultivate.progress + 1, 10);
    }
}
