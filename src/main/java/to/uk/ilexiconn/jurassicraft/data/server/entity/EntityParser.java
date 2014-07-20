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
        Collection<DinoEntry> dinos = new Gson().fromJson(new FileReader(getConfigFile()), new TypeToken<Collection<DinoEntry>>(){}.getType());
        for (DinoEntry dino : dinos)
        {
            addEntity(dino.name, Class.forName("to.uk.ilexiconn.jurassicraft.data.server.entity.Entity" + dino.name).asSubclass(EntityLiving.class), 0, 0);
            System.out.println("[JurassiCraft/SERVER] Registered dino " + dino.name + " [DinoProperties:{id:" + dino.id + ",health:" + dino.health + ",speed:" + dino.speed + ",scale:" + dino.scale + "}]");
        }
    }

    @SideOnly(Side.CLIENT)
    public void initClient() throws Exception
    {
        Collection<DinoEntry> dinos = new Gson().fromJson(new FileReader(getConfigFile()), new TypeToken<Collection<DinoEntry>>(){}.getType());
        for (DinoEntry dino : dinos)
        {
            getProxy().renderEntity(Class.forName("to.uk.ilexiconn.jurassicraft.data.server.entity.Entity" + dino.name).asSubclass(EntityLiving.class), (RenderLiving) Class.forName("to.uk.ilexiconn.jurassicraft.data.client.entity.Render" + dino.name).newInstance());
            System.out.println("[JurassiCraft/CLIENT] Registered renderer for " + dino.name);
        }
    }

    private File getConfigFile()
    {
        try
        {
            File tempFile = File.createTempFile("dinos", ".json");
            tempFile.deleteOnExit();
            InputStream inputStream = EntityParser.class.getResourceAsStream("/assets/jurassicraft/dinos.json");
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
