package com.axity.dinosaurpark.simulation;

import com.axity.dinosaurpark.monitoring.ParkMonitoring;
import com.axity.dinosaurpark.persistence.CsvWriter;
import com.axity.dinosaurpark.zone.PowerPlant;
import com.axity.dinosaurpark.monitoring.ParkMonitoring;
import java.util.Random;

public class SimulationEngine {
    private final ParkState state;
    private final EventScheduler scheduler;
    private final int totalSteps;

    public SimulationEngine(long seed, int totalSteps) {
        this.totalSteps = totalSteps;
        // Aquí estamos pasando los 3 argumentos que el constructor de arriba espera:
        // Sustituye tu línea actual por esta:
        this.state = new ParkState(
                new CsvWriter("ruta/donde/guardar/tu/archivo.csv"), // Le damos el string que pide
                new PowerPlant(100.0, 5.0, 0.01),  // Le damos los 3 números que pide
                seed                                                // La semilla
        );
        this.scheduler = new EventScheduler(seed, totalSteps);
    }

    public void run() {
        for (int step = 0; step < totalSteps; step++) {
            state.incrementStep();

            // A. LLEGADAS (Aquí llamarías a tu zona de llegada)
            // arrivalZone.processBatch(batchSize, state.getCsvWriter());

            // B. MOVIMIENTO DE TURISTAS
            // Lógica de movimiento en zonas...

            // C. TICKS DE ZONAS
            state.getPowerPlant().tick(state.getRng(), state.getCsvWriter());

            // D. EVENTO (El motor pregunta al scheduler si hay algo hoy)
            scheduler.checkForEvent(step).ifPresent(e -> e.execute(state, state.getRng()));

            // E. WORKERS
            // Lógica de guardias y técnicos...

            // F. MONITOREO
            ParkMonitoring.displaySnapshot(state);
        }
    }
}