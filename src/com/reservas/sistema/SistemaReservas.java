package com.reservas.sistema;

import com.reservas.modelo.Pelicula;
import com.reservas.modelo.Funcion;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PATRÓN SINGLETON
 * 
 * Clase que gestiona el sistema de reservas centralmente
 * Garantiza que solo exista UNA instancia en toda la aplicación
 * 
 * Características del Singleton:
 * 1. Constructor privado (nadie puede hacer new)
 * 2. Instancia estática privada
 * 3. Método público getInstance() para obtener la instancia
 * 
 * ¿Por qué Singleton aquí?
 * - Necesitamos un único catálogo de películas y funciones
 * - Evita inconsistencias (varias "versiones" del sistema)
 * - Punto de acceso global controlado
 */
public class SistemaReservas {
    // ============================================
    // ELEMENTOS DEL PATRÓN SINGLETON
    // ============================================
    
    /**
     * La ÚNICA instancia del sistema
     * - Es estática: pertenece a la clase, no a objetos individuales
     * - Es privada: solo esta clase puede accederla directamente
     */
    private static SistemaReservas instancia;
    
    // ============================================
    // ATRIBUTOS DEL SISTEMA
    // ============================================
    private List<Pelicula> peliculas;   // Catálogo completo de películas
    private List<Funcion> funciones;    // Todas las funciones programadas
    
    /**
     * Constructor PRIVADO
     * Esto previene que otras clases hagan: new SistemaReservas()
     * Es la clave del patrón Singleton
     */
    private SistemaReservas() {
        this.peliculas = new ArrayList<>();
        this.funciones = new ArrayList<>();
        
        // Aquí podríamos cargar datos desde una base de datos
        // o inicializar con datos predeterminados
    }
    
    /**
     * Método estático que devuelve la única instancia del sistema
     * Este es el ÚNICO punto de acceso al sistema
     * 
     * Funcionamiento:
     * - Primera vez: crea la instancia
     * - Siguientes veces: devuelve la misma instancia
     * 
     * @return La única instancia de SistemaReservas
     */
    public static SistemaReservas getInstance() {
        if (instancia == null) {
            // Solo se ejecuta la primera vez
            instancia = new SistemaReservas();
        }
        // Siempre retorna la misma instancia
        return instancia;
    }
    
    // ============================================
    // MÉTODOS DE GESTIÓN
    // ============================================
    
    /**
     * Agrega una película al catálogo del sistema
     * @param pelicula Película a agregar
     */
    public void agregarPelicula(Pelicula pelicula) {
        peliculas.add(pelicula);
        // En producción, aquí también se guardaría en base de datos
    }
    
    /**
     * Agrega una función (proyección) al sistema
     * @param funcion Función a agregar
     */
    public void agregarFuncion(Funcion funcion) {
        funciones.add(funcion);
        // En producción, aquí también se guardaría en base de datos
    }
    
    /**
     * Busca películas por título o género
     * Utiliza Stream API para filtrar de manera funcional
     * 
     * @param criterio Texto a buscar (título o género)
     * @return Lista de películas que coinciden con el criterio
     */
    public List<Pelicula> buscarPeliculas(String criterio) {
        return peliculas.stream()
            // Filtramos: incluimos películas cuyo título o género contengan el criterio
            .filter(p -> 
                p.getTitulo().toLowerCase().contains(criterio.toLowerCase()) ||
                p.getGenero().toLowerCase().contains(criterio.toLowerCase())
            )
            // Recolectamos los resultados en una nueva lista
            .collect(Collectors.toList());
    }
    
    /**
     * Obtiene todas las películas del sistema
     * Retorna una COPIA para prevenir modificaciones externas
     * @return Lista de todas las películas
     */
    public List<Pelicula> getPeliculas() { 
        return new ArrayList<>(peliculas); 
    }
    
    /**
     * Obtiene todas las funciones del sistema
     * Retorna una COPIA para prevenir modificaciones externas
     * @return Lista de todas las funciones
     */
    public List<Funcion> getFunciones() { 
        return new ArrayList<>(funciones); 
    }
}