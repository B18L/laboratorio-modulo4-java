package com.axity.dinosaurpark.zone;

import com.axity.dinosaurpark.persistence.CsvWriter;
import com.axity.dinosaurpark.model.Tourist;
import com.axity.dinosaurpark.model.TouristStatus;
import java.util.ArrayList;
import java.util.List;

public class ArrivalZone implements ParkZone {

    private final String name;
    private final int maxCapacity;
    private final List<Tourist> touristsInZone;

    public ArrivalZone(int maxCapacity) {
        this.name = "Lugar de Arribo";
        this.maxCapacity = maxCapacity;
        this.touristsInZone = new ArrayList<>();
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

    // Método propio de ArrivalZone
    public List<Tourist> processBatch(int batchSize, Object csvWriter) {
        List<Tourist> processedTourists = new ArrayList<>();
        int processed = 0;

        while (processed < batchSize && !touristsInZone.isEmpty()) {
            Tourist tourist = touristsInZone.get(0);
            tourist.setStatus(TouristStatus.IN_PARK);
            exit(tourist);

            processedTourists.add(tourist);
            processed++;
        }

        System.out.println("La cantidad de turistas al llegar al parque fue de " + processed + " turistas en el arribo.");
        return processedTourists;
    }
}
