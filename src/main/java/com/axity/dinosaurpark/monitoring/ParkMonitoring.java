package com.axity.dinosaurpark.monitoring;

import com.axity.dinosaurpark.simulation.ParkState;

public class ParkMonitoring {

    public static void displaySnapshot(ParkState state) {
        System.out.println("--- MONITOREO DEL PARQUE [Step: " + state.getCurrentStep() + "] ---");
        System.out.println("Turistas activos: " + state.countActiveTourists());
        System.out.println("Dinosaurios en encierro: " + state.countDinosaursInEnclosure());
        System.out.println("Energía disponible: " + state.getPowerPlant().getEnergyLevel() + "%");
        System.out.println("Ingresos: $" + state.getTotalRevenue());
        System.out.println("Gastos: $" + state.getTotalExpenses());
        System.out.println("---------------------------------------------------");
    }
}