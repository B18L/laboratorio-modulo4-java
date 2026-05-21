package com.axity.dinosaurpark.zone;

import java.util.Random;

public class PowerPlant {

    private final String name;
    private boolean operational;      // true = funcionando, false = apagón/falla
    private double currentEnergy;     // Nivel de energía actual
    private final double maxEnergy;   // Capacidad máxima de la planta
    private final double energyConsumptionPerTick; // Cuánto gasta por paso
    private final double failureProbability;       // Probabilidad de falla por paso

    public PowerPlant(double maxEnergy, double energyConsumptionPerTick, double failureProbability) {
        this.name = "Planta de Energía Central";
        this.operational = true; // Inicia funcionando perfectamente
        this.maxEnergy = maxEnergy;
        this.currentEnergy = maxEnergy; // Inicia llena
        this.energyConsumptionPerTick = energyConsumptionPerTick;
        this.failureProbability = failureProbability;
    }

    // El método del paso del tiempo
    public void tick(Random random, Object csvWriter) {
        if (!operational) {
            System.out.println("¡ALERTA! La planta de energía está fuera de servicio. Se requiere un técnico inmediatamente.");
            return;
        }

        // 1. Consumir energía del parque
        currentEnergy -= energyConsumptionPerTick;
        if (currentEnergy < 0) {
            currentEnergy = 0;
        }
        System.out.println("Planta de energía: Consumiendo " + energyConsumptionPerTick + " unidades. Energía disponible: " + currentEnergy);

        // 2. Verificar si ocurre una falla aleatoria (Apagón en este caso)
        // Usamos el mismo truco determinista del Random con la semilla
        if (random.nextDouble() < failureProbability) {
            triggerFailure(csvWriter);
        }
    }

    // Método para disparar la falla (Apagón masivo)
    public void triggerFailure(Object csvWriter) {
        this.operational = false;
        System.out.println("¡Ha ocurrido una falla crítica en la planta de energía!, ¡Apagón masivo en el parque!");
    }

    // El método de reparación que usará tu clase Technician
    public void repair() {
        this.operational = true;
        this.currentEnergy = maxEnergy; // Al repararla, reiniciamos el sistema al máximo
        System.out.println("Técnico: Sistema reiniciado con éxito. La planta de energía vuelve a estar operativa.");
    }

    // Getters para que el motor pueda monitorear el estado de la planta
    public String getName() {
        return name;
    }

    public boolean isOperational() {
        return operational;
    }

    public double getCurrentEnergy() {
        return currentEnergy;
    }

    public double getMaxEnergy() {
        return maxEnergy;
    }
}
