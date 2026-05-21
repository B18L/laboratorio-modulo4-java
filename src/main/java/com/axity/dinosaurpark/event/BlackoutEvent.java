package com.axity.dinosaurpark.event;

import com.axity.dinosaurpark.persistence.EventRecord;
import com.axity.dinosaurpark.persistence.ExpenseRecord;
import com.axity.dinosaurpark.simulation.ParkState;

import java.time.LocalDateTime;
import java.util.Random;

public class BlackoutEvent implements SimulationEvent {

    private final String name = "APAGON_MASIVO";
    private final String description = "Falla general en los generadores principales del parque.";

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
        // Añadir el csvWriter
        state.getPowerPlant().triggerFailure(state.getCsvWriter());

        // Registrar el gasto de $2000 por reparación de emergencia
        long expenseId = System.currentTimeMillis();
        ExpenseRecord expense = new ExpenseRecord(
                expenseId, "MANTENIMIENTO", 2000.0,
                "Reparación de emergencia por apagón masivo", LocalDateTime.now()
        );

        // Proteger la escritura por si el writer llega nulo
        if (state.getCsvWriter() != null) {
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
                "PowerPlant",
                LocalDateTime.now()
        );
    }
}