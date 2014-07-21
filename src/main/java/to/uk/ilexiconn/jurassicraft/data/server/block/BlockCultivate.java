package to.uk.ilexiconn.jurassicraft.data.server.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import to.uk.ilexiconn.jurassicraft.JurassiCraft;
import to.uk.ilexiconn.jurassicraft.Util;
import to.uk.ilexiconn.jurassicraft.data.server.tile.TileCultivate;

import java.util.List;
import java.util.Random;

public class BlockCultivate
{
    public static class Bottom extends BlockContainer
    {
        public static String[] colors = {"black", "red", "green", "brown", "blue", "purple", "cyan", "light_gray", "gray", "pink", "lime", "yellow", "light_blue", "magenta", "orange", "white"};
        @SideOnly(Side.CLIENT)
        public IIcon[] icons;
        public boolean isLit;

        public Bottom(boolean lit)
        {
            super(Material.cactus); //Yes..., Cactus xD
            setBlockName("cultivate_bottom_" + (lit ? "lit" : "idle"));
            setBlockTextureName(Util.getModId() + "cultivate");
            setCreativeTab(lit ? null : Util.getCreativeTab(0));
            setHardness(2f);
            setBlockBounds(0f, 0f, 0f, 1f, 2f, 1f);
            if (lit) setLightLevel(1.0f);
            isLit = lit;
        }

        @SideOnly(Side.CLIENT)
        public IIcon getIcon(int side, int meta)
        {
            if (meta < 0 || meta >= colors.length) meta = 0;
            return icons[meta];
        }

        @SideOnly(Side.CLIENT)
        public void getSubBlocks(Item item, CreativeTabs tab, List list)
        {
            for (int i = 0; i < colors.length; i++) list.add(new ItemStack(item, 1, i));
        }

        @SideOnly(Side.CLIENT)
        public void registerBlockIcons(IIconRegister iconRegister)
        {
            icons = new IIcon[colors.length];

            for (int i = 0; i < colors.length; i++)
            {
                String name = getTextureName();

                if (colors[i] != null) name = name + "_" + colors[i];

                icons[i] = iconRegister.registerIcon(name);
            }
        }

        public int damageDropped(int meta)
        {
            return meta;
        }

        public int getRenderType()
        {
            return -1;
        }

        @SideOnly(Side.CLIENT)
        public int getRenderBlockPass()
        {
            return 1;
        }

        public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta)
        {
            world.setBlockToAir(x, y + 1, z);
        }

        public boolean isOpaqueCube()
        {
            return false;
        }

        public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float t, float h, float k)
        {
            player.openGui(JurassiCraft.instance, 0, world, x, y, z);
            updateBlockStateWithBottom(world, x, y, z, !isLit);
            return true;
        }

        public Item getItemDropped(int meta, Random random, int fortune)
        {
            return Item.getItemFromBlock(Util.getBlock(0));
        }

        @SideOnly(Side.CLIENT)
        public Item getItem(World world, int x, int y, int z)
        {
            return Item.getItemFromBlock(Util.getBlock(0));
        }

        public TileEntity createNewTileEntity(World world, int meta)
        {
            return new TileCultivate();
        }
    }

    public static class Top extends Block
    {
        public static String[] colors = {"black", "red", "green", "brown", "blue", "purple", "cyan", "light_gray", "gray", "pink", "lime", "yellow", "light_blue", "magenta", "orange", "white"};
        @SideOnly(Side.CLIENT)
        public IIcon[] icons;
        public boolean isLit;

        public Top(boolean lit)
        {
            super(Material.cactus);
            setBlockName("cultivate_top_" + (lit ? "lit" : "idle"));
            setBlockTextureName(Util.getModId() + "cultivate");
            setCreativeTab(null);
            setHardness(2f);
            setBlockBounds(0f, -1, 0f, 1f, 1f, 1f);
            if (lit) setLightLevel(1.0f);
            isLit = lit;
        }

        @SideOnly(Side.CLIENT)
        public IIcon getIcon(int side, int meta)
        {
            return icons[meta];
        }

        @SideOnly(Side.CLIENT)
        public void registerBlockIcons(IIconRegister iconRegister)
        {
            icons = new IIcon[colors.length];
            for (int i = 0; i < colors.length; i++)
            {
                String name = getTextureName();

                if (colors[i] != null) name = name + "_" + colors[i];

                icons[i] = iconRegister.registerIcon(name);
            }
        }

        public boolean renderAsNormalBlock()
        {
            return false;
        }

        public int getRenderType()
        {
            return -1;
        }

        @SideOnly(Side.CLIENT)
        public int getRenderBlockPass()
        {
            return 1;
        }

        public Item getItemDropped(int meta, Random random, int fortune)
        {
            return Item.getItemFromBlock(Util.getBlock(0));
        }

        @SideOnly(Side.CLIENT)
        public Item getItem(World world, int x, int y, int z)
        {
            return Item.getItemFromBlock(Util.getBlock(0));
        }

        public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta)
        {
            world.setBlockToAir(x, y - 1, z);
        }

        public boolean isOpaqueCube()
        {
            return false;
        }

        public int damageDropped(int i)
        {
            return i;
        }

        public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float t, float h, float k)
        {
            player.openGui(JurassiCraft.instance, 0, world, x, y - 1, z);
            updateBlockStateWithTop(world, x, y, z, !isLit);
            return true;
        }
    }

    public static void updateBlockStateWithBottom(World world, int x, int y, int z, boolean lit)
    {
        int metadata = world.getBlockMetadata(x, y, z);
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        world.setBlock(x, y, z, Util.getBlock(lit ? 2 : 0));
        world.setBlock(x, y + 1, z, Util.getBlock(lit ? 3 : 1));

        if (tileEntity != null)
        {
            tileEntity.validate();
            world.setTileEntity(x, y, z, tileEntity);
        }

        world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
        world.setBlockMetadataWithNotify(x, y + 1, z, metadata, 2);
    }

    public static void updateBlockStateWithTop(World world, int x, int y, int z, boolean lit)
    {
        int metadata = world.getBlockMetadata(x, y, z);

        world.setBlock(x, y - 1, z, Util.getBlock(lit ? 2 : 0));
        world.setBlock(x, y, z, Util.getBlock(lit ? 3 : 1));

        world.setBlockMetadataWithNotify(x, y - 1, z, metadata, 2);
        world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
    }

    public static int getRotation(World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        return tileEntity instanceof TileCultivate ? ((TileCultivate) tileEntity).rotation : 0;
    }

    public static void setRotation(World world, int x, int y, int z, int rotation)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileCultivate) ((TileCultivate) tileEntity).rotation = rotation;
    }
}
