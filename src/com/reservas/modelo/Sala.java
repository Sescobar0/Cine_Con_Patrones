package com.reservas.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Clase Sala: representa una sala de cine con sus asientos
 * Tiene la responsabilidad de inicializar todos sus asientos
 */
public class Sala {
    private String id;              // Identificador único de la sala
    private int capacidad;          // Número total de asientos
    private List<Asiento> asientos; // Lista de todos los asientos
    
    /**
     * Constructor que crea una sala con capacidad específica
     * @param capacidad Número de asientos de la sala
     */
    public Sala(int capacidad) {
        this.id = UUID.randomUUID().toString();
        this.capacidad = capacidad;
        this.asientos = new ArrayList<>();
        inicializarAsientos(); // Creamos todos los asientos automáticamente
    }
    
    /**
     * Método privado que crea todos los asientos de la sala
     * Se ejecuta automáticamente en el constructor
     */
    private void inicializarAsientos() {
        // Creamos asientos numerados: A1, A2, A3, ..., A50
        for (int i = 1; i <= capacidad; i++) {
            asientos.add(new Asiento("A" + i));
        }
    }
    
    // Getters
    public String getId() { return id; }
    public int getCapacidad() { return capacidad; }
    public List<Asiento> getAsientos() { return asientos; }
}