package com.reservas.modelo;

/**
 * Clase Asiento: representa un asiento individual en una sala
 * Tiene estado (ocupado/libre) y operaciones para cambiar ese estado
 */
public class Asiento {
    private String numero;      // Número del asiento (ej: "A1", "B5")
    private boolean ocupado;    // Estado: true = ocupado, false = libre
    
    /**
     * Constructor que crea un asiento libre
     * @param numero Número identificador del asiento
     */
    public Asiento(String numero) {
        this.numero = numero;
        this.ocupado = false; // Por defecto, el asiento está libre
    }
    
    /**
     * Marca el asiento como ocupado
     * Se llama cuando se confirma una reserva
     */
    public void reservar() { 
        this.ocupado = true; 
    }
    
    /**
     * Marca el asiento como libre
     * Se llama cuando se cancela una reserva
     */
    public void liberar() { 
        this.ocupado = false; 
    }
    
    // Getters
    public String getNumero() { return numero; }
    public boolean isOcupado() { return ocupado; }
}