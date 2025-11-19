package com.reservas.modelo;

/**
 * Clase Administrador: usuario con privilegios especiales
 * Puede gestionar funciones y generar reportes
 */
public class Administrador extends Usuario {
    
    /**
     * Constructor del administrador
     */
    public Administrador(String nombre, String email, String password) {
        super(nombre, email, password);
    }
    
    /**
     * Panel específico para administradores
     */
    @Override
    public void mostrarPanel() {
        System.out.println("=== Panel de Administrador ===");
        System.out.println("Acceso completo al sistema");
    }
    
    /**
     * Funcionalidad exclusiva: gestionar funciones
     * (Agregar, modificar, eliminar funciones de cine)
     */
    public void gestionarFunciones() {
        System.out.println("Gestionando funciones...");
        // Aquí iría la lógica para CRUD de funciones
    }
    
    /**
     * Funcionalidad exclusiva: generar reportes
     * (Ventas, ocupación de salas, películas más vistas, etc.)
     */
    public void generarReportes() {
        System.out.println("Generando reportes...");
        // Aquí iría la lógica para generar reportes
    }
}