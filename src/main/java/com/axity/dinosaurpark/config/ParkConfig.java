package com.axity.dinosaurpark.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ParkConfig {

    private static ParkConfig instance;
    private final Properties props;

    // Constructor PRIVADO — Carga el archivo park.properties
    private ParkConfig() {
        this.props = new Properties();

        // Carga el archivo park.properties desde la carpeta resources
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("park.properties")) {
            if (input == null) {
                System.out.println("Te pido una disculpa, no se pudo encontrar park.properties");
                return;
            }
            // Carga todas las llaves y valores del archivo en el objeto props
            this.props.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Punto de acceso global — Patrón Singleton
    public static synchronized ParkConfig getInstance() {
        if (instance == null) {
            instance = new ParkConfig();
        }
        return instance;
    }

    // Métodos de lectura para obtener los valores del properties

    public String getString(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        String value = props.getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        return Integer.parseInt(value.trim());
    }

    public double getDouble(String key, double defaultValue) {
        String value = props.getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        return Double.parseDouble(value.trim());
    }

    // Métodos específicos

    public long getSeed() {
        // Lee la propiedad "simulation.seed", si no existe usa 42 como valor por defecto
        return Long.parseLong(props.getProperty("simulation.seed", "42").trim());
    }

    public int getTotalSteps() {
        // Lee la propiedad "simulation.totalSteps", si no existe usa 100 por defecto
        return Integer.parseInt(props.getProperty("simulation.totalSteps", "100").trim());
    }

    // Solo para tests. Permite resetear la instancia entre tests
    public static void resetForTesting() {
        instance = null;
    }
}