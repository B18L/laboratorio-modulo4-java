package com.axity.dinosaurpark.config;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParkConfigTest {
    @Test
    void testGetInstance() {
        ParkConfig config1 = ParkConfig.getInstance();
        ParkConfig config2 = ParkConfig.getInstance();

        // Verifica que siempre sea la misma instancia (Singleton)
        assertSame(config1, config2);
    }

    @Test
    void testGetDefaultValues() {
        // Verifica que los métodos de lectura funcionan
        int steps = ParkConfig.getInstance().getInt("simulation.totalSteps", 100);
        assertTrue(steps > 0);
    }
}