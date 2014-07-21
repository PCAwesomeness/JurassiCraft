package to.uk.ilexiconn.jurassicraft.data.server.entity;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import org.apache.commons.io.IOUtils;
import to.uk.ilexiconn.jurassicraft.Util;

import java.io.*;
import java.util.Collection;

public class EntityParser extends Util
{
    public void init() throws Exception
    {
        Collection<EntityEntry> entries = new Gson().fromJson(new FileReader(getConfig()), new TypeToken<Collection<EntityEntry>>(){}.getType());
        for (EntityEntry entity : entries)
        {
            addEntity(entity.name, Class.forName("to.uk.ilexiconn.jurassicraft.data.server.entity.Entity" + entity.name).asSubclass(EntityLiving.class), 0, 0);
            System.out.println("[JurassiCraft/SERVER] Registered dino " + entity.name + " [DinoProperties:{id:" + entity.id + ",health:" + entity.health + ",speed:" + entity.speed + ",scale:" + entity.scale + "}]");
        }
    }

    @SideOnly(Side.CLIENT)
    public void initClient() throws Exception
    {
        Collection<EntityEntry> entries = new Gson().fromJson(new FileReader(getConfig()), new TypeToken<Collection<EntityEntry>>(){}.getType());
        for (EntityEntry entity : entries)
        {
            Util.getProxy().renderEntity(Class.forName("to.uk.ilexiconn.jurassicraft.data.server.entity.Entity" + entity.name).asSubclass(EntityLiving.class), Class.forName("to.uk.ilexiconn.jurassicraft.data.client.entity.Render" + entity.name).asSubclass(RenderLiving.class).newInstance());
            System.out.println("[JurassiCraft/CLIENT] Registered renderer for " + entity.name);
        }
    }

    private File getConfig()
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
