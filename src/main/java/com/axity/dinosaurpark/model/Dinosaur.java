package com.axity.dinosaurpark.model;

public abstract class Dinosaur {

    private final int id;
    private final String name;
    private final String species;
    private DinosaurStatus status;
    private final double feedingCostPerDay;

    // Constructor que usarán las subclases mediante super()
    public Dinosaur(int id, String name, String species, double feedingCostPerDay) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.status = DinosaurStatus.IN_ENCLOSURE; // Inicia seguro en su hábitat
        this.feedingCostPerDay = feedingCostPerDay;
    }

    // Métodos abstractos que cada subclase debe implementar a su modo
    public abstract String getDiet();
    public abstract double getDangerLevel();

    // Métodos concretos compartidos por todos
    public void escape() {
        this.status = DinosaurStatus.ESCAPED;
    }

    public void recapture() {
        this.status = DinosaurStatus.RECAPTURED;
    }

    public void returnToEnclosure() {
        this.status = DinosaurStatus.IN_ENCLOSURE;
    }

    // Getters esenciales
    public int getId() { return id; }
    public String getName() { return name; }
    public String getSpecies() { return species; }
    public DinosaurStatus getStatus() { return status; }
    public double getFeedingCostPerDay() { return feedingCostPerDay; }
}
