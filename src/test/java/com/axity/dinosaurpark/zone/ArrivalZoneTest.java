package com.axity.dinosaurpark.zone;

import com.axity.dinosaurpark.model.Tourist;
import com.axity.dinosaurpark.model.TouristStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrivalZoneTest {

    private ArrivalZone arrivalZone;

    @BeforeEach
    void setUp() {
        // Capacidad inicial de 2 turistas para pruebas controladas
        arrivalZone = new ArrivalZone(2);
    }

    @Test
    void testCapacidadYEntrada() {
        assertTrue(arrivalZone.hasCapacity());

        // Pasamos el ID y el Nombre tal como lo exige tu constructor
        arrivalZone.enter(new Tourist(1, "Turista 1"));
        arrivalZone.enter(new Tourist(2, "Turista 2"));

        assertFalse(arrivalZone.hasCapacity(), "La zona debería estar llena al llegar a 2");
        assertEquals(2, arrivalZone.getCurrentOccupancy());
    }

    @Test
    void testProcessBatch_DeberiaCambiarEstadoYRemover() {
        // Arrange
        arrivalZone.enter(new Tourist(1, "Turista 1"));
        arrivalZone.enter(new Tourist(2, "Turista 2"));

        // Act: Procesar un lote de 1 turista
        List<Tourist> procesados = arrivalZone.processBatch(1, null);

        // Assert
        assertEquals(1, procesados.size());
        assertEquals(TouristStatus.IN_PARK, procesados.get(0).getStatus(), "El estatus debe cambiar a IN_PARK");
        assertEquals(1, arrivalZone.getCurrentOccupancy(), "Debería quedar 1 turista en la zona");
    }

    @Test
    void testProcessBatch_NoDeberiaFallarSiEstaVacio() {
        // Act: Intentar procesar cuando no hay nadie
        List<Tourist> procesados = arrivalZone.processBatch(5, null);

        // Assert
        assertTrue(procesados.isEmpty(), "La lista de procesados debería estar vacía");
    }
}