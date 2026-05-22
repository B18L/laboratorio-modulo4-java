package com.axity.dinosaurpark.simulation;

import com.axity.dinosaurpark.config.ParkConfig;
import com.axity.dinosaurpark.model.Tourist;
import com.axity.dinosaurpark.monitoring.ParkMonitoring;
import com.axity.dinosaurpark.persistence.CsvWriter;
import com.axity.dinosaurpark.zone.ArrivalZone;
import com.axity.dinosaurpark.zone.PowerPlant;
import java.util.ArrayList;
import java.util.List;

public class SimulationEngine {
    private final ParkState state;
    private final EventScheduler scheduler;
    private final int totalSteps;
    private final ArrivalZone arrivalZone;
    private final int batchSize;
    private final List<Tourist> activeTourists;

    public SimulationEngine(ParkConfig config) {
        this.totalSteps = config.getTotalSteps();
        this.batchSize = config.getArrivalBatchSize();
        this.activeTourists = new ArrayList<>();
        this.arrivalZone = new ArrivalZone(config.getArrivalMaxCapacity());

        // Inicialización del estado
        this.state = new ParkState(
                new CsvWriter(config.getOutputDirectory()),
                new PowerPlant(config.getPowerPlantInitialEnergy(), config.getPowerPlantConsumption(), config.getPowerPlantFailureProb()),
                config.getSeed()
        );

        this.scheduler = new EventScheduler(config.getSeed(), config.getTotalSteps());
    }

    public void run() {
        for (int step = 0; step < totalSteps; step++) {
            state.incrementStep();

            // A. LLEGADAS
            // Llama al método corregido que devuelve List<Tourist>
            List<Tourist> arrived = arrivalZone.processBatch(batchSize, state.getCsvWriter());
            activeTourists.addAll(arrived);

            // B. MOVIMIENTO DE TURISTAS
            // Llama a los métodos de otras zonas (CentralHub, etc.)
            for (Tourist t : activeTourists) {
                // Lógica de movimiento pendiente de implementar si es posible
            }

            // C. TICKS DE ZONAS
            state.getPowerPlant().tick(state.getRng(), state.getCsvWriter());

            // D. EVENTO
            scheduler.checkForEvent(step).ifPresent(e -> e.execute(state, state.getRng()));

            // E. WORKERS
            // Lógica de guardias y técnicos
            // guards.forEach(g -> g.recaptureEscapedDinosaurs(dinosaurs));
            // technicians.forEach(t -> t.repairIfNeeded(state.getPowerPlant()));

            // F. MONITOREO
            ParkMonitoring.displaySnapshot(state);
        }
    }
}