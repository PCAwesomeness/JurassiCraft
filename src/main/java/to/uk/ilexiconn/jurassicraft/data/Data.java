package to.uk.ilexiconn.jurassicraft.data;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import to.uk.ilexiconn.jurassicraft.JurassiCraft;
import to.uk.ilexiconn.jurassicraft.Util;
import to.uk.ilexiconn.jurassicraft.data.client.block.RenderCultivateBlock;
import to.uk.ilexiconn.jurassicraft.data.client.block.RenderEggBlock;
import to.uk.ilexiconn.jurassicraft.data.client.event.RenderBlockBoundsEvent;
import to.uk.ilexiconn.jurassicraft.data.client.item.RenderCultivateItem;
import to.uk.ilexiconn.jurassicraft.data.packet.PacketUpdateProgress;
import to.uk.ilexiconn.jurassicraft.data.server.block.BlockCultivate;
import to.uk.ilexiconn.jurassicraft.data.server.creativetab.TabJurassiCraft;
import to.uk.ilexiconn.jurassicraft.data.server.gui.GuiHandler;
import to.uk.ilexiconn.jurassicraft.data.server.item.ItemBlockCultivate;
import to.uk.ilexiconn.jurassicraft.data.server.tile.TileCultivate;
import to.uk.ilexiconn.jurassicraft.data.server.tile.TileEgg;

public class Data extends Util
{
    public void init()
    {
        { //CreativeTabs
            addCreativeTab(0, new TabJurassiCraft());
        }
        { //Items

        }
        { //Blocks
            addBlockWithSubBlocks(0, new BlockCultivate.Bottom(false), TileCultivate.class, ItemBlockCultivate.class, true);
            addBlock(1, new BlockCultivate.Top(false));
            addBlockWithSubBlocks(2, new BlockCultivate.Bottom(true), TileCultivate.class, ItemBlockCultivate.class, false);
            addBlock(3, new BlockCultivate.Top(true));
        }
        { //Entities

        }
        { //Recipes
            for (int i = 0; i < BlockCultivate.Bottom.colors.length; i++)
            {
                addShapelessRecipe(new ItemStack(getBlock(0), 1, i), new ItemStack(Items.dye, 1, i), new ItemStack(getBlock(0)));
                addShapedRecipe(new ItemStack(getBlock(0), 1, i), "GDG", "GWG", "III", 'I', Items.iron_ingot, 'G', Blocks.glass, 'D', new ItemStack(Items.dye, 1, i), 'W', Items.water_bucket);
            }
        }
        { //Events
            addGuiHandler(new GuiHandler());
            addEvent(new RenderBlockBoundsEvent(), false);
        }
        { //Biomes

        }
        { //Packets
            JurassiCraft.packetPipeline.registerPacket(PacketUpdateProgress.class);
        }
    }

    @SideOnly(Side.CLIENT)
    public void initClient()
    {
        { //Item Renderers
            getProxy().renderItem(Item.getItemFromBlock(getBlock(0)), new RenderCultivateItem());
        }
        { //Block Renderers
            getProxy().renderBlock(TileCultivate.class, new RenderCultivateBlock());
            getProxy().renderBlock(TileEgg.class, new RenderEggBlock());
        }
        { //Entity Renderers

        }
        { //Capes

        }
    }
}
