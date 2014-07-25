package to.uk.ilexiconn.jurassicraft.data.server.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import to.uk.ilexiconn.jurassicraft.api.Util;
import to.uk.ilexiconn.jurassicraft.api.IBlockHighlight;
import to.uk.ilexiconn.jurassicraft.data.server.entity.EntityEntry;
import to.uk.ilexiconn.jurassicraft.data.server.tile.TileEgg;

import java.util.List;

public class BlockEgg extends BlockContainer implements IBlockHighlight
{
    public EntityEntry entityEntry;
    public AxisAlignedBB[] boxes =
    {
        AxisAlignedBB.getBoundingBox(0.34375f,  0.0f,       0.34375f,   0.65625f,   0.0625f,    0.65625f),
        AxisAlignedBB.getBoundingBox(0.3125f,   0.0625f,    0.3125f,    0.6875f,    0.375f,     0.6875f),
        AxisAlignedBB.getBoundingBox(0.34375f,  0.379f,     0.34375f,   0.65625f,   0.5f,       0.65625f),
        AxisAlignedBB.getBoundingBox(0.40625f,  0.504f,     0.40625f,   0.59375f,   0.5625f,    0.59375f)
    };

    public BlockEgg(EntityEntry entity)
    {
        super(Material.dragonEgg);
        setBlockName("egg_" + entity.name.toLowerCase());
        setBlockTextureName(Util.getModId() + "egg_" + entity.name.toLowerCase());
        setCreativeTab(Util.getCreativeTab(0));
        setHardness(2.0f);
        entityEntry = entity;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return -1;
    }

    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEgg(entityEntry);
    }

    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB box, List list, Entity entity)
    {
        AxisAlignedBB[] aabbs = boxes;
        for (AxisAlignedBB aabb : aabbs)
        {
            AxisAlignedBB aabbTmp = aabb.getOffsetBoundingBox(x, y, z);
            if (box.intersectsWith(aabbTmp)) list.add(aabbTmp);
        }
    }

    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 origin, Vec3 direction)
    {
        AxisAlignedBB[] aabbs = boxes;
        MovingObjectPosition closest = null;
        for (AxisAlignedBB aabb : aabbs)
        {
            MovingObjectPosition mop = aabb.getOffsetBoundingBox(x, y, z).calculateIntercept(origin, direction);
            if (mop != null)
            {
                if (closest != null && mop.hitVec.distanceTo(origin) < closest.hitVec.distanceTo(origin)) closest = mop;
                else closest = mop;
            }
        }
        if (closest != null)
        {
            closest.blockX = x;
            closest.blockY = y;
            closest.blockZ = z;
        }
        return closest;
    }

    public AxisAlignedBB[] getBoxes(World world, int x, int y, int z, EntityPlayer player)
    {
        return boxes;
    }
}
