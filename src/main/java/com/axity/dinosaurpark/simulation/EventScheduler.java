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

        // Si la simulación es muy corta, evitamos errores de rango
        if (totalSteps > 5) {
            // Sorteamos un paso aleatorio para cada uno de nuestros 3 eventos de la Fase 4
            int stepApagon = rng.nextInt(totalSteps);
            int stepTormenta = rng.nextInt(totalSteps);
            int stepEscape = rng.nextInt(totalSteps);

            // Los registramos en el mapa "Minuto/Paso -> Evento"
            scheduledEvents.put(stepApagon, new BlackoutEvent());
            scheduledEvents.put(stepTormenta, new StormEvent());
            scheduledEvents.put(stepEscape, new DinosaurEscapeEvent());
        }
    }

    public Optional<SimulationEvent> checkForEvent(int step) {
        return Optional.ofNullable(scheduledEvents.get(step));
    }
}