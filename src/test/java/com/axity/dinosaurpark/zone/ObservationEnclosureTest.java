package com.axity.dinosaurpark.zone;

import com.axity.dinosaurpark.model.Tourist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ObservationEnclosureTest {

    private ObservationEnclosure enclosure;
    private Random mockRandom;

    @BeforeEach
    void setUp() {
        // Inicializamos con un tipo de experiencia válida de tu Enum
        enclosure = new ObservationEnclosure("Encierro T-Rex", 1, ExperienceType.BASIC);
        mockRandom = mock(Random.class);
    }

    @Test
    void testCapacidad() {
        assertTrue(enclosure.hasCapacity());

        enclosure.enter(new Tourist(1, "Turista A"));

        assertFalse(enclosure.hasCapacity(), "La zona debería estar llena");
        assertEquals(1, enclosure.getCurrentOccupancy());
    }

    @Test
    void testVisit_DeberiaGestionarEntradaYSalida() {
        Tourist t1 = new Tourist(1, "Turista A");

        // Simulamos que el random devuelve un valor.
        // Con BASIC(1,3), el rango es 3. nextInt(3) devuelve 0, 1 o 2.
        when(mockRandom.nextInt(anyInt())).thenReturn(0);

        enclosure.visit(t1, mockRandom, null);

        // Verificamos que el turista entró y salió (la lista debe estar vacía)
        assertEquals(0, enclosure.getCurrentOccupancy(), "El turista debió salir después de la visita");
    }

    @Test
    void testConductSurvey_VerificarPuntaje() {
        Tourist t1 = new Tourist(1, "Turista A");

        // Forzamos el random para que devuelva un valor controlado
        when(mockRandom.nextInt(anyInt())).thenReturn(2);

        // Ejecutamos la encuesta
        assertDoesNotThrow(() -> enclosure.conductSurvey(t1, mockRandom));

        // Verificamos que el Random fue invocado al menos una vez
        verify(mockRandom, times(1)).nextInt(anyInt());
    }

    @Test
    void testGetExperienceType() {
        assertEquals(ExperienceType.BASIC, enclosure.getExperienceType());
    }
}