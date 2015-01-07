package org.rohawks.critacola.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Handles reading properties from application.properties.
 */
public class PropertiesManager {
    public static final String[] PROPERTIES_LOCATIONS = {
        "application.properties", "META-INF/application.properties",
        "target/classes/META-INF/application.properties"};

    private static PropertiesManager instance = new PropertiesManager();
    private Properties properties;
    private boolean loadedProperties = false;

    private PropertiesManager() {
        properties = new Properties();
        ClassLoader loader = getClass().getClassLoader();

        // Load the properties file, trying each location in turn
        for( String location:PROPERTIES_LOCATIONS ) {
            InputStream inputStream = loader.getResourceAsStream( location );
            if( inputStream == null ) {
                System.err.println( "[PropertiesManager()] Couldn't load" +
                        " from " + location );
                continue;
            }
            try {
                properties.load( inputStream );
                loadedProperties = true;
            } catch( IOException ex ) {
                ex.printStackTrace();
            }
        }
    }

    public static PropertiesManager getInstance() {
        return instance;
    }

    public String getProperty( String key ) {
        if( !loadedProperties ) {
            throw new IllegalStateException( "Properties could not be loaded" );
        }
        return properties.getProperty( key );
    }
}

