package com.axity.dinosaurpark.zone;

import com.axity.dinosaurpark.model.Tourist;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CentralHub implements ParkZone {

    private final String name;
    private final int maxCapacity;
    private final List<Tourist> touristsInZone;
    private final double souvenirProbability; // Recordar que la probabilidad es porcentual de 0 a 1

    public CentralHub(int maxCapacity, double souvenirProbability) {
        this.name = "Recinto Central";
        this.maxCapacity = maxCapacity;
        this.touristsInZone = new ArrayList<>();
        this.souvenirProbability = souvenirProbability;
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

    // Método de CentralHub para simular la visita y compra de recuerdos
    public void visit(Tourist tourist, Random random, Object csvWriter) {
        // Genera un decimal aleatorio entre 0.0 y 1.0
        if (random.nextDouble() < souvenirProbability) {
            System.out.println("El turista " + tourist.getName() + " decidió comprar un souvenir en el Recinto Central");
        } else {
            System.out.println("El turista " + tourist.getName() + " visitó el Recinto Central pero decidió no comprar nada.");
        }
    }
}
