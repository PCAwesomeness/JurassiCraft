package to.uk.ilexiconn.jurassicraft.data.server.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class TabJurassiCraft extends CreativeTabs
{
    public TabJurassiCraft()
    {
        super("JurassiCraft");
    }

    public Item getTabIconItem()
    {
        return Items.bone;
    }

    public String getTranslatedTabLabel()
    {
        return getTabLabel();
    }
}
