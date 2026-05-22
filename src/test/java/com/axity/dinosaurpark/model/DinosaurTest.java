package com.axity.dinosaurpark.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DinosaurTest {
    @Test
    void testStatusChanges() {
        // Crear una instancia de prueba o mock
        Dinosaur d = new HerbivoreDinosaur(1, "Rex", "T-Rex");

        d.escape();
        assertEquals(DinosaurStatus.ESCAPED, d.getStatus());

        d.returnToEnclosure();
        assertEquals(DinosaurStatus.IN_ENCLOSURE, d.getStatus());
    }
}