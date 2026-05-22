package com.axity.dinosaurpark.event;

import static org.mockito.Mockito.*;

import com.axity.dinosaurpark.simulation.ParkState;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Random;

public class DinosaurEscapeEventTest {
    @Test
    void testExecuteEscape() {
        // Arrange
        ParkState mockState = mock(ParkState.class);
        when(mockState.getDinosaurs()).thenReturn(new ArrayList<>()); // O añade un dino mock
        when(mockState.getTourists()).thenReturn(new ArrayList<>());

        DinosaurEscapeEvent event = new DinosaurEscapeEvent();

        // Act
        event.execute(mockState, new Random());

        // Assert
        // Verificar que se haya intentado registrar el evento si el escritor existe
        verify(mockState, atLeastOnce()).getCsvWriter();
    }
}
