package com.axity.dinosaurpark.persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {

    private final String outputDir;
    private final String revenuePath;
    private final String expensePath;
    private final String eventPath;

    // Constructor que configura rutas y prepara los archivos con sus headers
    public CsvWriter(String outputDir) {
        this.outputDir = outputDir;
        this.revenuePath = outputDir + File.separator + "revenues.csv";
        this.expensePath = outputDir + File.separator + "expenses.csv";
        this.eventPath = outputDir + File.separator + "events.csv";

        initDirectoryAndFiles();
    }

    // Crea la carpeta y limpia/sobreescribe los archivos escribiendo el Header
    private void initDirectoryAndFiles() {
        try {
            // 1. Crear el directorio si no existe
            File directory = new File(outputDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 2. Inicializar archivos sobreescribiendo (false) y colocando headers
            initFile(revenuePath, "id,type,amount,touristId,zone,timestamp");
            initFile(expensePath, "id,type,amount,description,timestamp");
            initFile(eventPath, "step,eventName,description,affectedEntities,timestamp");

        } catch (IOException e) {
            System.err.println(" Error crítico al inicializar los archivos CSV: " + e.getMessage());
        }
    }

    // Método utilitario para escribir el encabezado inicial de un archivo
    private void initFile(String path, String header) throws IOException {
        // false significa sobreescribir el archivo completo al iniciar la simulación
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, false))) {
            writer.write(header);
            writer.newLine();
        }
    }

    // Método para agregar una fila al archivo de ingresos (Revenues)
    public void appendRevenue(RevenueRecord r) {
        // Aquí usar true para ir acumulando las filas conforme pasa la simulación
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(revenuePath, true))) {
            writer.write(r.toCsvLine());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en revenues.csv: " + e.getMessage());
        }
    }

    // Método para agregar una fila al archivo de gastos (Expenses)
    public void appendExpense(ExpenseRecord e) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(expensePath, true))) {
            writer.write(e.toCsvLine());
            writer.newLine();
        } catch (IOException ex) {
            System.err.println("Error al escribir en expenses.csv: " + ex.getMessage());
        }
    }

    // Método para agregar una fila al archivo de eventos (Events)
    public void appendEvent(EventRecord ev) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(eventPath, true))) {
            writer.write(ev.toCsvLine());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en events.csv: " + e.getMessage());
        }
    }
}
