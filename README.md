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

Patrones de diseño implementados

-Singleton. Se implementó el patrón Singleton en la clase ParkConfig para asegurar que el sistema tenga un único puinto de acceso a la configuración del parque.
Esto evita la carga  redundante del archivo park.properties y garantiza la consistencia de los parámetros (como totalSteps o seed) en todo
el ciclo de vida de la simulación.

-Patron Strategy. Se implementó el patrón Strategy para mantener la diversidad de eventos en la simulación. La interfaz SimulationEvent actúa como la
estrategia común.

La arquitectura del sistema está construida sobre tres pilares fundamentales que garantizan su escalabilidad y robustez:

Gestión Centralizada: Utiliza el patrón Singleton para el manejo de configuraciones globales, asegurando que todos los componentes de la simulación operen bajo los mismos parámetros definidos en el archivo park.properties.

Modelado Jerárquico: Implementa Polimorfismo y Herencia en la jerarquía de dinosaurios (Dinosaur), permitiendo que las clases especializadas definan comportamientos únicos de dieta y nivel de peligrosidad, manteniendo una interfaz común para la simulación.

Arquitectura de Eventos: Aplica el patrón de diseño Strategy a través de la interfaz SimulationEvent. Esto permite que el motor de simulación ejecute una variedad de eventos (como apagones o escapes) de forma modular, permitiendo extender la funcionalidad del parque con nuevos eventos sin necesidad de alterar el núcleo del motor.

El sistema registra el historial de operaciones y gastos de mantenimiento mediante un módulo de persistencia en tiempo real, lo que permite un monitoreo detallado del estado del parque durante cada paso (step) de la simulación.








**Comentarios finales.
Los ultimos minutos que pasaron de las 12 fue porque olvidaba que estaba en otra rama, así que cuando me dí cuenta ya superaba la hora pero no por mucho.
Tuve muchos problemas al ejecutar mvn test porque me marcaba algun error de la version o de jacoco o de mockito, hice varios cambios pero no pude solucionarlo
y tampoco supe al 100% la razón. Iban desde las versiones de las dependencias, hasta que mi java --version era Java 25 el que tenia instalado y me queria a obligar
a instalar otra version como 17 o 21 cuando en el pom lo especifiqué así que no supe la razón ya que no había tenido este problema antes.
Se hizo lo que se pudo :D, una muy buena prueba, me quedo de tarea lo que quedó pendiente.

Muchas gracias por todo!!! Este curso ha sido maravilloso así como los involucrados, desde los profesores, hasta el presonal involucrado. Aprendí muchísimo, incluso durante el examen y me emociona saber que aún me falta mucho por aprender!!! Saludos!!!

