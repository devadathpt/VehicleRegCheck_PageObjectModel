package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadConfigClass  {

    public static FileInputStream  configfile(LoadConfig l) throws IOException
    {
        String s = System.getProperty("user.dir")+"//src//test//java//Config//config.properties";
        FileInputStream fs = new FileInputStream(s);
        return l.getConfig(fs);

    }


}
