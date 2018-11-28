package net.rhuanrocha;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class FileSystemConfigSource implements ConfigSource {
    private final String FILE_CONFIG = "/etc/mp-speaker/config/configuration.properties";

    @Override
    public Map<String, String> getProperties() {

        try(InputStream in = new FileInputStream( FILE_CONFIG)){

            Properties properties = new Properties();
            properties.load( in );

            Map<String, String> map = new HashMap<>();
            properties.stringPropertyNames()
                    .stream()
                    .forEach(key-> map.put(key, properties.getProperty(key)));

            return map;

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public Set<String> getPropertyNames() {

        try(InputStream in = new FileInputStream( FILE_CONFIG)){

            Properties properties = new Properties();
            properties.load( in );

            return properties.stringPropertyNames();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getOrdinal() {
        return 300;
    }

    @Override
    public String getValue(String s) {

        try(InputStream in = new FileInputStream( FILE_CONFIG)){

            Properties properties = new Properties();
            properties.load( in );

            return properties.getProperty(s);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getName() {
        return "FileSystemConfigSource";
    }




}
