package to.uk.ilexiconn.jurassicraft.data.server.tile;

import net.minecraft.tileentity.TileEntity;
import to.uk.ilexiconn.jurassicraft.data.server.entity.EntityEntry;

public class TileEgg extends TileEntity
{
    public EntityEntry entityEntry;

    public TileEgg(EntityEntry entry)
    {
        entityEntry = entry;
    }
}
