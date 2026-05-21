package com.axity.dinosaurpark.simulation;

import com.axity.dinosaurpark.persistence.CsvWriter;
import com.axity.dinosaurpark.zone.PowerPlant;
import com.axity.dinosaurpark.model.Tourist;
import com.axity.dinosaurpark.model.TouristStatus;
import com.axity.dinosaurpark.model.Dinosaur;
import com.axity.dinosaurpark.model.DinosaurStatus;

import java.util.ArrayList;
import java.util.List;

public class ParkState {

    private final CsvWriter csvWriter;
    private final PowerPlant powerPlant;
    private final List<Tourist> tourists;
    private final List<Dinosaur> dinosaurs;
    private double totalRevenue;
    private double totalExpenses;
    private int currentStep;

    // Constructor base que utilizará el SimulationEngine
    public ParkState(CsvWriter csvWriter, PowerPlant powerPlant) {
        this.csvWriter = csvWriter;
        this.powerPlant = powerPlant;
        this.tourists = new ArrayList<>();
        this.dinosaurs = new ArrayList<>();
        this.totalRevenue = 0.0;
        this.totalExpenses = 0.0;
        this.currentStep = 0;
    }

    public PowerPlant getPowerPlant() {
        return powerPlant;
    }

    public CsvWriter getCsvWriter() {
        return csvWriter;
    }

    public List<Tourist> getTourists() {
        return tourists;
    }

    public List<Dinosaur> getDinosaurs() {
        return dinosaurs;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public int countActiveTourists() {
        return (int) tourists.stream()
                .filter(t -> t.getStatus() == TouristStatus.IN_PARK)
                .count();
    }

    public int countDinosaursInEnclosure() {
        return (int) dinosaurs.stream()
                .filter(d -> d.getStatus() == DinosaurStatus.IN_ENCLOSURE)
                .count();
    }

    public void incrementStep() {
        this.currentStep++;
    }

    public void addExpense(double amount) {
        this.totalExpenses += amount;
    }

    public void addRevenue(double amount) {
        this.totalRevenue += amount;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }
}