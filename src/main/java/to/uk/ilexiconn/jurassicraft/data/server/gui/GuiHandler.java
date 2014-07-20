package to.uk.ilexiconn.jurassicraft.data.server.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import to.uk.ilexiconn.jurassicraft.data.client.gui.GuiCultivate;
import to.uk.ilexiconn.jurassicraft.data.server.gui.container.ContainerCultivate;
import to.uk.ilexiconn.jurassicraft.data.server.tile.TileCultivate;

public class GuiHandler implements IGuiHandler
{
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileCultivate) return new ContainerCultivate(player.inventory, (TileCultivate) tileEntity);
        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileCultivate) return new GuiCultivate(player.inventory, (TileCultivate) tileEntity);
        return null;
    }
}
