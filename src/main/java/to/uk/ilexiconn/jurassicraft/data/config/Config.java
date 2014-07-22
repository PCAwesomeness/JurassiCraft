package to.uk.ilexiconn.jurassicraft.data.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class Config
{
    public ConfigData config;

    public void readConfig(File configFile) throws Exception
    {
        if (!configFile.exists())
        {
            if (!configFile.createNewFile()) throw new RuntimeException("[JurassiCraft] Failed to create config file!");
            else writeConfig(configFile);
        }
        config = new Gson().fromJson(new FileReader(configFile), ConfigData.class);
    }

    private void writeConfig(File configFile)
    {
        FileOutputStream outputStream;
        try
        {
            outputStream = new FileOutputStream(configFile);
            outputStream.write(new GsonBuilder().setPrettyPrinting().create().toJson(new ConfigData()).getBytes());
            outputStream.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
