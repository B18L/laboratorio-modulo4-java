package com.axity.dinosaurpark.event;

import com.axity.dinosaurpark.persistence.EventRecord;
import com.axity.dinosaurpark.persistence.ExpenseRecord;
import com.axity.dinosaurpark.simulation.ParkState;
import com.axity.dinosaurpark.model.TouristStatus;
import java.time.LocalDateTime;
import java.util.Random;

public class StormEvent implements SimulationEvent {

    private final String name = "TORMENTA_TORRENCIAL";
    private final String description = "Lluvia severa que obliga a evacuar las atracciones del parque.";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute(ParkState state, Random rng) {
        // 1. Evacuar a los turistas activos usando tu Enum real
        state.getTourists().stream()
                .filter(t -> t.getStatus() == TouristStatus.IN_PARK)
                .forEach(t -> t.recordVisit("Evacuación"));

        // 2. Simular el registro del gasto en el CSV si ya está inicializado
        if (state.getCsvWriter() != null) {
            ExpenseRecord expense = new ExpenseRecord(
                    System.currentTimeMillis(),
                    "OPERACION",
                    500.0,
                    "Logística de evacuación por tormenta",
                    LocalDateTime.now()
            );
            state.getCsvWriter().appendExpense(expense);
            state.getCsvWriter().appendEvent(toRecord(state.getCurrentStep()));
        }
    }

    @Override
    public EventRecord toRecord(long step) {
        return new EventRecord(
                step,
                name,
                description,
                "Parque Completo",
                LocalDateTime.now()
        );
    }
}