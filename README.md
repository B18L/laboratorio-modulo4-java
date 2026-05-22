# Parque Turístico de Dinosaurios - Proyecto Básico

## Explicación general del sistema
Este proyecto consiste en una simulación secuencial en Java 17 de un parque temático de dinosaurios. 
El sistema gestiona entidades como turistas, dinosaurios, trabajadores y diversas zonas del parque, simulando el flujo diario, 
la venta de entradas, el control de energía y la gestión de incidentes básicos.

## Herramientas utilizadas
- **Lenguaje:** Java 17
- **Gestor de dependencias:** Maven
- **Frameworks de pruebas:** JUnit 5, Mockito
- **Cobertura:** JaCoCo

## Instrucciones de configuración
1. Proyecto de Java 17 y Maven.
2. Clone el repositorio.
3. Configure los parámetros de simulación en `src/main/resources/park.properties`.

Patrones de diseño implementados
-Singleton. Se implementó el patrón Singleton en la clase ParkConfig para asegurar que el sistema tenga un único puinto de acceso a la configuración del parque.
Esto evita la carga  redundante del archivo park.properties y garantiza la consistencia de los parámetros (como totalSteps o seed) en todo
el ciclo de vida de la simulación.

-Patron Strategy. Se implementó el patrón Strategy para mantener la diversidad de eventos en la simulación. La interfaz SimulationEvent actúa como la
estrategia común.
