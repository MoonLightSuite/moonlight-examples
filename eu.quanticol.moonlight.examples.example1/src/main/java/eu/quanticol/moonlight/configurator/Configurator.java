package eu.quanticol.moonlight.configurator;

import java.io.InputStream;
import java.util.Properties;

public class Configurator {
    public static final Properties PROPERTIES = load();
    public static final String STALIRO_PATH = PROPERTIES.getProperty("STALIRO_PATH");
    public static final String BREACH_PATH = PROPERTIES.getProperty("BREACH_PATH");

    private static Properties load() {
        Properties prop = new Properties();
        try {
            InputStream in = Configurator.class.getResourceAsStream("config.properties");
            prop.load(in);
            in.close();
        } catch (Exception e) {
        }
        return prop;
    }
}
