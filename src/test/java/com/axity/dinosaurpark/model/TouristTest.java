package com.axity.dinosaurpark.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TouristTest {

    private Tourist tourist;

    @BeforeEach
    void setUp() {
        // Inicializamos un turista de prueba antes de cada test
        tourist = new Tourist(101, "Juan Perez");
    }

    @Test
    void testInicializacionCorrecta() {
        assertEquals(101, tourist.getId());
        assertEquals("Juan Perez", tourist.getName());
        assertEquals(TouristStatus.WAITING, tourist.getStatus());
        assertEquals(0.0, tourist.getMoneySpent());
        assertTrue(tourist.getVisitedZones().isEmpty());
    }

    @Test
    void testSpend_DeberiaAcumularGasto() {
        tourist.spend(50.0);
        tourist.spend(25.5);

        assertEquals(75.5, tourist.getMoneySpent(), 0.001);
    }

    @Test
    void testRecordVisit_DeberiaAgregarZonaALista() {
        tourist.recordVisit("Zona Jurásica");
        tourist.recordVisit("Laboratorio");

        assertEquals(2, tourist.getVisitedZones().size());
        assertTrue(tourist.getVisitedZones().contains("Zona Jurásica"));
        assertTrue(tourist.getVisitedZones().contains("Laboratorio"));
    }

    @Test
    void testSetStatus_DeberiaCambiarEstado() {
        // Prueba el cambio a IN_PARK
        tourist.setStatus(TouristStatus.IN_PARK);
        assertEquals(TouristStatus.IN_PARK, tourist.getStatus());

        // Prueba el cambio a ATACKED (que parece ser un estado importante en tu parque)
        tourist.setStatus(TouristStatus.ATTACKED);
        assertEquals(TouristStatus.ATTACKED, tourist.getStatus());

        // Prueba el cambio a EXITED
        tourist.setStatus(TouristStatus.EXITED);
        assertEquals(TouristStatus.EXITED, tourist.getStatus());
    }
}