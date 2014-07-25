package to.uk.ilexiconn.jurassicraft.data.server.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import to.uk.ilexiconn.jurassicraft.Util;
import to.uk.ilexiconn.jurassicraft.data.server.block.BlockCultivate;

public class ItemBlockCultivate extends ItemBlock
{
    public String[] colors = {"black", "red", "green", "brown", "blue", "purple", "cyan", "light_gray", "gray", "pink", "lime", "yellow", "light_blue", "magenta", "orange", "white"};

    public ItemBlockCultivate(Block block)
    {
        super(block);
        setHasSubtypes(true);
        setMaxStackSize(16);
    }

    public String getItemStackDisplayName(ItemStack itemStack)
    {
        String name = "";
        for (String item : colors[itemStack.getItemDamage()].replaceAll("_", " ").split(" ")) name = name + String.valueOf(item.charAt(0)).toUpperCase() + item.substring(1) + " ";
        return name + "Cultivate";
    }

    public int getMetadata(int meta)
    {
        return meta;
    }

    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
        if (world.getBlock(x, y + 1, z).isReplaceable(world, x, y, z))
        {
            super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
            world.setBlock(x, y + 1, z, Util.getBlock(1));
            world.setBlockMetadataWithNotify(x, y + 1, z, world.getBlockMetadata(x, y, z), 2);
            BlockCultivate.setRotation(world, x, y, z, MathHelper.floor_double((double) ((player.rotationYaw * 4F) / 360F) + 0.5D) & 3);
            return true;
        }
        else return false;
    }
}
