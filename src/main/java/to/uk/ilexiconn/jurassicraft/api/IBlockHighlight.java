package to.uk.ilexiconn.jurassicraft.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public interface IBlockHighlight
{
    AxisAlignedBB[] getBoxes(World world, int x, int y, int z, EntityPlayer player);
}
