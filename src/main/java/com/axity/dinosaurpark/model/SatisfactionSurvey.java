package com.axity.dinosaurpark.model;

public class SatisfactionSurvey {

    private final int touristId;
    private final String enclosureName;
    private final int score; // Rango esperado de 1 a 5

    public SatisfactionSurvey(int touristId, String enclosureName, int score) {
        this.touristId = touristId;
        this.enclosureName = enclosureName;
        // Una pequeña validación rápida para asegurar que esté entre 1 y 5
        this.score = Math.max(1, Math.min(5, score));
    }

    // Solo getters
    public int getTouristId() { return touristId; }
    public String getEnclosureName() { return enclosureName; }
    public int getScore() { return score; }
}
