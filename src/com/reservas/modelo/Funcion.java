package com.reservas.modelo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Clase Funcion: representa una proyección específica de una película
 * Relaciona: Película + Horario + Sala
 */
public class Funcion {
    private String id;                  // Identificador único de la función
    private Pelicula pelicula;          // Película que se proyecta
    private LocalDateTime horario;      // Fecha y hora de la función
    private Sala sala;                  // Sala donde se proyecta
    
    /**
     * Constructor que crea una función
     * @param pelicula Película a proyectar
     * @param horario Fecha y hora
     * @param sala Sala de proyección
     */
    public Funcion(Pelicula pelicula, LocalDateTime horario, Sala sala) {
        this.id = UUID.randomUUID().toString();
        this.pelicula = pelicula;
        this.horario = horario;
        this.sala = sala;
    }
    
    /**
     * Verifica si un conjunto de asientos está disponible
     * @param asientos Lista de asientos a verificar
     * @return true si TODOS los asientos están libres, false si alguno está ocupado
     */
    public boolean verificarDisponibilidad(List<Asiento> asientos) {
        // Usamos Stream API para verificar que ningún asiento esté ocupado
        // noneMatch retorna true si NINGUNO cumple la condición (ocupado)
        return asientos.stream().noneMatch(Asiento::isOcupado);
    }
    
    // Getters
    public String getId() { return id; }
    public Pelicula getPelicula() { return pelicula; }
    public LocalDateTime getHorario() { return horario; }
    public Sala getSala() { return sala; }
}
