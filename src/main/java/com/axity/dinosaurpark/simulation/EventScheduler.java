package com.axity.dinosaurpark.simulation;

import com.axity.dinosaurpark.event.BlackoutEvent;
import com.axity.dinosaurpark.event.DinosaurEscapeEvent;
import com.axity.dinosaurpark.event.SimulationEvent;
import com.axity.dinosaurpark.event.StormEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class EventScheduler {

    private final Map<Integer, SimulationEvent> scheduledEvents;

    //Constructor del planificador de eventos determinista.
    public EventScheduler(long seed, int totalSteps) {
        this.scheduledEvents = new HashMap<>();

        // Inicializar el objeto Random con la semilla fija obligatoria
        Random rng = new Random(seed);

        if (totalSteps > 5) {
            // Sortea un paso aleatorio para cada uno de los 3 eventos de la Fase 4 (eventos)
            int stepApagon = rng.nextInt(totalSteps);
            int stepTormenta = rng.nextInt(totalSteps);
            int stepEscape = rng.nextInt(totalSteps);

            // Registra en el mapa "Minuto/Paso -> Evento"
            scheduledEvents.put(stepApagon, new BlackoutEvent());
            scheduledEvents.put(stepTormenta, new StormEvent());
            scheduledEvents.put(stepEscape, new DinosaurEscapeEvent());
        }
    }

    public Optional<SimulationEvent> checkForEvent(int step) {
        return Optional.ofNullable(scheduledEvents.get(step));
    }
}