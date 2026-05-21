package com.axity.dinosaurpark.zone;

import com.axity.dinosaurpark.model.Tourist;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ObservationEnclosure implements ParkZone {

    private final String name;
    private final int maxCapacity;
    private final List<Tourist> touristsInZone;
    private final ExperienceType experienceType;

    public ObservationEnclosure(String name, int maxCapacity, ExperienceType experienceType) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.touristsInZone = new ArrayList<>();
        this.experienceType = experienceType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean hasCapacity() {
        return touristsInZone.size() < maxCapacity;
    }

    @Override
    public int getCurrentOccupancy() {
        return touristsInZone.size();
    }

    @Override
    public int getMaxCapacity() {
        return maxCapacity;
    }

    @Override
    public void enter(Tourist tourist) {
        if (hasCapacity()) {
            touristsInZone.add(tourist);
        }
    }

    @Override
    public void exit(Tourist tourist) {
        touristsInZone.remove(tourist);
    }

    // Método propio de esta zona para simular la visita
    public void visit(Tourist tourist, Random random, Object csvWriter) {
        if (hasCapacity()) {
            enter(tourist);
            System.out.println("El turista " + tourist.getName() + " está observando los dinosaurios en el encierro: " + name);
            conductSurvey(tourist, random);
            exit(tourist);
        } else {
            System.out.println("El encierro " + name + " está lleno. El turista " + tourist.getName() + " no pudo entrar.");
        }
    }

    // Método de la encuesta que lee los rangos del Enum
    public void conductSurvey(Tourist tourist, Random random) {
        int min = experienceType.getMinScore();
        int max = experienceType.getMaxScore();

        int score = random.nextInt((max - min) + 1) + min;

        System.out.println("Encuesta: El turista " + tourist.getName() + " calificó el encierro [" + name + "] (" + experienceType + ") con " + score + " estrellas.");
    }

    public ExperienceType getExperienceType() {
        return experienceType;
    }
}