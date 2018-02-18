package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@FunctionalInterface
public interface LoadConfig {

      FileInputStream getConfig(FileInputStream fs) throws IOException;
}


