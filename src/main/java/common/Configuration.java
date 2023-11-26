package common;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
@Log4j2
public class Configuration {

    private static Configuration instance = null;
    private Properties p;

    public Configuration() {
        p = new Properties();
        try(InputStream propertiesStream = this.getClass().getResourceAsStream(Constantes.MYSQL_PROPERTIES_XML)) {
            p.loadFromXML(propertiesStream);
        } catch (IOException e) {
            log.error(Constantes.ERROR_CARGAR_FICHERO_CONFIG, e);
        }
    }

    public String getProperty(String key) {
        return p.getProperty(key);
    }
}
