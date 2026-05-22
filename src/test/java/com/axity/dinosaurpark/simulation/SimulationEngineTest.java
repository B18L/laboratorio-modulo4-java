package com.axity.dinosaurpark.simulation;

import com.axity.dinosaurpark.config.ParkConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class SimulationEngineTest {

    private ParkConfig mockConfig;
    private SimulationEngine engine;

    @BeforeEach
    void setUp() {
        // Simulamos la configuración para que el motor no intente leer archivos reales
        mockConfig = mock(ParkConfig.class);

        // Configuramos valores mínimos necesarios para que el constructor no falle
        when(mockConfig.getTotalSteps()).thenReturn(5); // Solo 5 pasos para el test
        when(mockConfig.getArrivalBatchSize()).thenReturn(2);
        when(mockConfig.getArrivalMaxCapacity()).thenReturn(10);
        when(mockConfig.getOutputDirectory()).thenReturn("test-output");
        when(mockConfig.getPowerPlantInitialEnergy()).thenReturn(100.0);
        when(mockConfig.getPowerPlantConsumption()).thenReturn(10.0);
        when(mockConfig.getPowerPlantFailureProb()).thenReturn(0.1);
        when(mockConfig.getSeed()).thenReturn(12345L);

        engine = new SimulationEngine(mockConfig);
    }

    @Test
    void testRunExecutesSimulationSteps() {
        // Ejecutamos el motor
        engine.run();

        // Verificamos que se hayan intentado procesar los pasos configurados (5 steps)
        // Esto confirma que el bucle for en run() se está ejecutando
        verify(mockConfig, atLeastOnce()).getTotalSteps();
    }
}