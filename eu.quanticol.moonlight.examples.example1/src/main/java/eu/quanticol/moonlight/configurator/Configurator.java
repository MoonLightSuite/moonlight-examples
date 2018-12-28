package eu.quanticol.moonlight.configurator;

import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

class Configurator {
    private static final Properties PROPERTIES = load();
    static final String STALIRO_PATH = PROPERTIES.getProperty("STALIRO_PATH");
    static final String BREACH_PATH = PROPERTIES.getProperty("BREACH_PATH");

    private static Properties load() {
        Properties prop = new Properties();
        try {
            String configFile = InetAddress.getLocalHost().getHostName() + ".properties";
            InputStream in = Configurator.class.getResourceAsStream(configFile);
            prop.load(in);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }
}
