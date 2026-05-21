package com.axity.dinosaurpark.model;

import java.util.ArrayList;
import java.util.List;

public class Tourist {

    private final int id;
    private final String name;
    private TouristStatus status;
    private double moneySpent;
    private final List<String> visitedZones;

    // Constructor para inicializar al turista listo para el parque
    public Tourist(int id, String name) {
        this.id = id;
        this.name = name;
        this.status = TouristStatus.WAITING; // Inicia esperando entrar
        this.moneySpent = 0.0;
        this.visitedZones = new ArrayList<>();
    }

    // Métodos de comportamiento
    public void spend(double amount) {
        this.moneySpent += amount;
    }

    public void recordVisit(String zoneName) {
        this.visitedZones.add(zoneName);
    }

    // Getters y Setters necesarios
    public int getId() { return id; }
    public String getName() { return name; }

    public TouristStatus getStatus() { return status; }
    public void setStatus(TouristStatus status) { this.status = status; }

    public double getMoneySpent() { return moneySpent; }
    public List<String> getVisitedZones() { return visitedZones; }
}
