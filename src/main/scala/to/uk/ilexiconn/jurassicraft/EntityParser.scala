package to.uk.ilexiconn.jurassicraft

import java.io._
import java.util

import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.client.renderer.entity.RenderLiving
import net.minecraft.entity.EntityLiving
import org.apache.commons.io.IOUtils
import scala.collection.JavaConversions._

class EntityParser extends Util
{
    def init()
    {
        val dinos: util.Collection[EntityEntry] = new Gson().fromJson(new FileReader(getConfig), new TypeToken[util.Collection[EntityEntry]]{}.getType)
        for (dino <- dinos)
        {
            addEntity(dino.name, Class.forName("to.uk.ilexiconn.jurassicraft.data.server.entity.Entity" + dino.name).asSubclass(classOf[EntityLiving]), 0, 0)
            System.out.println("[JurassiCraft/SERVER] Registered dino " + dino.name + " [DinoProperties:{id:" + dino.id + ",health:" + dino.health + ",speed:" + dino.speed + ",scale:" + dino.scale + "}]")
        }
    }

    @SideOnly(Side.CLIENT)
    def initClient()
    {
        val dinos: util.Collection[EntityEntry] = new Gson().fromJson(new FileReader(getConfig), new TypeToken[util.Collection[EntityEntry]]{}.getType)
        for (dino <- dinos)
        {
            Util.getProxy.renderEntity(Class.forName("to.uk.ilexiconn.jurassicraft.data.server.entity.Entity" + dino.name).asSubclass(classOf[EntityLiving]), Class.forName("to.uk.ilexiconn.jurassicraft.data.client.entity.Render" + dino.name).newInstance().asInstanceOf[RenderLiving])
            System.out.println("[JurassiCraft/CLIENT] Registered renderer for " + dino.name)
        }
    }

    def getConfig : File =
    {
        val tempTile: File = File.createTempFile("entities", ".json")
        tempTile.deleteOnExit()
        val inputStream: InputStream = getClass.getResourceAsStream("/assets/jurassicraft/entities.json")
        val outputStream: OutputStream = new FileOutputStream(tempTile)
        IOUtils.copy(inputStream, outputStream)
        tempTile
    }
}
