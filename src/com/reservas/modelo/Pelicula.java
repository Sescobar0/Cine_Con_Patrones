package com.reservas.modelo;

import java.util.UUID;

/**
 * Clase Pelicula: representa una película en el catálogo
 * Es una entidad de dominio simple sin lógica compleja
 */
public class Pelicula {
    private String id;          // Identificador único
    private String titulo;      // Título de la película
    private String genero;      // Género (Acción, Drama, Sci-Fi, etc.)
    private int duracion;       // Duración en minutos
    
    /**
     * Constructor que crea una película
     * @param titulo Título de la película
     * @param genero Género cinematográfico
     * @param duracion Duración en minutos
     */
    public Pelicula(String titulo, String genero, int duracion) {
        this.id = UUID.randomUUID().toString(); // ID único generado automáticamente
        this.titulo = titulo;
        this.genero = genero;
        this.duracion = duracion;
    }
    
    // Getters: solo lectura de atributos
    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getGenero() { return genero; }
    public int getDuracion() { return duracion; }
}
