package com.axity.dinosaurpark.zone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PowerPlantTest {

    private PowerPlant powerPlant;
    private Random mockRandom;

    @BeforeEach
    void setUp() {
        // Configuramos: 100 max, 10 consumo, 0.1 probabilidad de falla
        powerPlant = new PowerPlant(100.0, 10.0, 0.1);
        mockRandom = mock(Random.class);
    }

    @Test
    void testTickReducesEnergy() {
        // En un tick, debería pasar de 100 a 90
        powerPlant.tick(mockRandom, null);
        assertEquals(90.0, powerPlant.getCurrentEnergy(), "La energía debería reducirse en 10 unidades");
    }

    @Test
    void testTriggerFailure() {
        // Simulamos una probabilidad baja que dispara la falla
        when(mockRandom.nextDouble()).thenReturn(0.05);

        powerPlant.tick(mockRandom, null);

        assertFalse(powerPlant.isOperational(), "La planta debería estar fuera de servicio tras la falla");
    }

    @Test
    void testRepairResetsPlant() {
        // Primero forzamos una falla
        powerPlant.triggerFailure(null);
        assertFalse(powerPlant.isOperational());

        // Reparamos
        powerPlant.repair();

        assertTrue(powerPlant.isOperational(), "La planta debería volver a estar operativa tras la reparación");
        assertEquals(100.0, powerPlant.getCurrentEnergy(), "La energía debería resetearse al máximo tras reparar");
    }
}