package to.uk.ilexiconn.jurassicraft.data.server.entity;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import org.apache.commons.io.IOUtils;
import to.uk.ilexiconn.jurassicraft.Util;
import to.uk.ilexiconn.jurassicraft.data.client.item.RenderEggItem;
import to.uk.ilexiconn.jurassicraft.data.server.block.BlockEgg;
import to.uk.ilexiconn.jurassicraft.data.server.tile.TileEgg;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;

public class EntityParser extends Util
{
    public void init() throws Exception
    {
        Collection<EntityEntry> entries = new Gson().fromJson(new FileReader(getTempFile()), new TypeToken<Collection<EntityEntry>>(){}.getType());
        for (EntityEntry entity : entries)
        {
            addBlock(500 + entity.id, new BlockEgg(entity));
            //addEntity(entity.name, Class.forName("to.uk.ilexiconn.jurassicraft.data.server.entity.Entity" + entity.name).asSubclass(EntityLiving.class), 0, 0);
            System.out.println("[JurassiCraft/SERVER] Registered entity " + entity.name + " with properties:" + Arrays.asList("scale", entity.scale, "speed", entity.speed, "health", entity.health, "strength", entity.strength));
        }
    }

    @SideOnly(Side.CLIENT)
    public void initClient() throws Exception
    {
        addTileEntity(TileEgg.class);
        Collection<EntityEntry> entries = new Gson().fromJson(new FileReader(getTempFile()), new TypeToken<Collection<EntityEntry>>(){}.getType());
        for (EntityEntry entity : entries)
        {
            getProxy().renderItem(Item.getItemFromBlock(getBlock(500 + entity.id)), new RenderEggItem(entity));
            //Util.getProxy().renderEntity(Class.forName("to.uk.ilexiconn.jurassicraft.data.server.entity.Entity" + entity.name).asSubclass(EntityLiving.class), Class.forName("to.uk.ilexiconn.jurassicraft.data.client.entity.Render" + entity.name).asSubclass(RenderLiving.class).newInstance());
        }
    }

    private File getTempFile()
    {
        try
        {
            File tempFile = File.createTempFile("entities", ".json");
            tempFile.deleteOnExit();
            InputStream inputStream = EntityParser.class.getResourceAsStream("/assets/jurassicraft/entities.json");
            OutputStream outputStream = new FileOutputStream(tempFile);
            IOUtils.copy(inputStream, outputStream);
            return tempFile;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
