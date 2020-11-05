package by.bivis.settings;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    public static String getSetting(String Property) throws IOException {
        File cfgFile = new File("./settings/tokens.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(cfgFile));
        return properties.getProperty(Property);
    }
}
