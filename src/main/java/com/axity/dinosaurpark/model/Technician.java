package com.axity.dinosaurpark.model;

import com.axity.dinosaurpark.zone.PowerPlant;

public class Technician extends Worker {

    // El constructor define un salario fijo para los técnicos, para este caso, 350.0
    public Technician(int id, String name) {
        super(id, name, 350.0);
    }

    @Override
    public String getRole() {
        return "TECHNICIAN";
    }

    // Si la planta está caída, la repara de inmediato
    public void repairIfNeeded(PowerPlant plant) {
        //if (!plant.isOperational()) {
        //    plant.repair();
        //}
    }
}