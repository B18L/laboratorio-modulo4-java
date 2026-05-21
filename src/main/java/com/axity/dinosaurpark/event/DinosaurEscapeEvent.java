package com.axity.dinosaurpark.event;

import com.axity.dinosaurpark.persistence.EventRecord;
import com.axity.dinosaurpark.simulation.ParkState;
import com.axity.dinosaurpark.model.Dinosaur;
import com.axity.dinosaurpark.model.DinosaurStatus;
import com.axity.dinosaurpark.model.Tourist;
import com.axity.dinosaurpark.model.TouristStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class DinosaurEscapeEvent implements SimulationEvent {
    private final String name = "ESCAPE_DINOSAURIO";
    private final String description = "¡Alerta roja! Un dinosaurio ha roto la seguridad de su encierro. ¡Sálvese quien pueda!";

    @Override public String getName() { return name; }
    @Override public String getDescription() { return description; }

    @Override
    public void execute(ParkState state, Random rng) {
        List<Dinosaur> inEnclosure = state.getDinosaurs().stream()
                .filter(d -> d.getStatus() == DinosaurStatus.IN_ENCLOSURE)
                .toList();

        if (!inEnclosure.isEmpty()) {
            Dinosaur escapedDino = inEnclosure.get(rng.nextInt(inEnclosure.size()));
            escapedDino.escape();

            double dangerLevel = escapedDino.getDangerLevel();
            if (rng.nextDouble() < dangerLevel) {
                List<Tourist> inPark = state.getTourists().stream()
                        .filter(t -> t.getStatus() == TouristStatus.IN_PARK)
                        .toList();

                if (!inPark.isEmpty()) {
                    Tourist victim = inPark.get(rng.nextInt(inPark.size()));
                    victim.setStatus(TouristStatus.ATTACKED);
                }
            }
        }

        if (state.getCsvWriter() != null) {
            state.getCsvWriter().appendEvent(toRecord(state.getCurrentStep()));
        }
    }

    @Override
    public EventRecord toRecord(long step) {
        return new EventRecord(step, name, description, "Encierros y Turistas", LocalDateTime.now());
    }
}