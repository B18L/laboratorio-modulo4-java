package com.axity.dinosaurpark.zone;

import com.axity.dinosaurpark.model.Tourist;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BathroomZone implements ParkZone {

    private final String name;
    private final int maxCapacity;
    private final List<Tourist> touristsInZone;

    // Lista para controlar cuánto tiempo le queda a cada turista en el baño
    private final List<Integer> remainingTimes;

    private final int useDurationSteps; // Cuántos ticks dura el servicio
    private final double spaProbability;  // Probabilidad de pagar el SPA

    public BathroomZone(int maxCapacity, int useDurationSteps, double spaProbability) {
        this.name = "Baños y Servicios";
        this.maxCapacity = maxCapacity;
        this.touristsInZone = new ArrayList<>();
        this.remainingTimes = new ArrayList<>();
        this.useDurationSteps = useDurationSteps;
        this.spaProbability = spaProbability;
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
            remainingTimes.add(useDurationSteps); // Le asignamos su tiempo inicial
        }
    }

    @Override
    public void exit(Tourist tourist) {
        int index = touristsInZone.indexOf(tourist);
        if (index != -1) {
            touristsInZone.remove(index);
            remainingTimes.remove(index); // Quitamos también su cronómetro
        }
    }

    // El método clave que pide la guía para intentar entrar y calcular el SPA
    public boolean tryEnter(Tourist tourist, Random random, Object csvWriter) {
        if (hasCapacity()) {
            enter(tourist);

            // Simulando si consume el servicio de SPA adicional
            if (random.nextDouble() < spaProbability) {
                System.out.println("El turista " + tourist.getName() + " entró a los baños y pagó el servicio adicional de SPA");
            } else {
                System.out.println("El turista " + tourist.getName() + " entró a los baños para uso estándar.");
            }
            return true;
        }
        System.out.println("El turista " + tourist.getName() + " quiso entrar al baño, pero estaba lleno.");
        return false;
    }

    // El método para el paso del tiempo
    public void tick(Random random, Object csvWriter) {
        // Recorremos de atrás para adelante para evitar problemas al eliminar elementos de la lista en el ciclo
        for (int i = touristsInZone.size() - 1; i >= 0; i--) {
            int timeLeft = remainingTimes.get(i) - 1;
            remainingTimes.set(i, timeLeft); // Restamos un paso de tiempo

            // Si se le acabó el tiempo, el turista sale del baño
            if (timeLeft <= 0) {
                Tourist tourist = touristsInZone.get(i);
                System.out.println("Al turista " + tourist.getName() + " se le terminó el tiempo y salió de los baños.");
                exit(tourist);
            }
        }
    }
}
