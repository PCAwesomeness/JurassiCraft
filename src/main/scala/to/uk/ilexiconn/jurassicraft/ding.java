package to.uk.ilexiconn.jurassicraft;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.util.Collection;

public class ding
{
    public void init() throws Exception
    {
        Collection<EntityEntry> dinos = new Gson().fromJson(new FileReader(new File("")), new TypeToken<Collection<EntityEntry>>(){}.getType());
        for (EntityEntry dino : dinos)
        {

        }
    }
}
