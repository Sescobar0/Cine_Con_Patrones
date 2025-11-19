package com.reservas.modelo;

import java.util.UUID;

/**
 * Clase abstracta Usuario que representa a cualquier usuario del sistema
 * 
 * PATRÓN FACTORY METHOD INTEGRADO:
 * Los métodos estáticos crearCliente() y crearAdministrador()
 * encapsulan la creación de usuarios específicos
 */
public abstract class Usuario {
    // Atributos protegidos: accesibles por las subclases
    protected String id;         // Identificador único del usuario
    protected String nombre;     // Nombre completo
    protected String email;      // Correo electrónico
    protected String password;   // Contraseña (en producción debería estar encriptada)
    
    /**
     * Constructor protegido: solo accesible por subclases
     * y por los métodos factory de esta misma clase
     * @param nombre Nombre del usuario
     * @param email Correo electrónico
     * @param password Contraseña
     */
    protected Usuario(String nombre, String email, String password) {
        this.id = UUID.randomUUID().toString(); // Generamos un ID único
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }
    
    // ============================================
    // PATRÓN FACTORY METHOD
    // ============================================
    /**
     * Método factory estático para crear un Cliente
     * Ventaja: Centraliza la lógica de creación
     * Si necesitamos validaciones o inicializaciones especiales,
     * solo modificamos este método
     * 
     * @param nombre Nombre del cliente
     * @param email Email del cliente
     * @param password Contraseña del cliente
     * @return Nueva instancia de Cliente
     */
    public static Cliente crearCliente(String nombre, String email, String password) {
        // Aquí podríamos agregar validaciones:
        // - Verificar formato del email
        // - Validar fortaleza de la contraseña
        // - Registrar en un log de auditoría
        return new Cliente(nombre, email, password);
    }
    
    /**
     * Método factory estático para crear un Administrador
     * @param nombre Nombre del administrador
     * @param email Email del administrador
     * @param password Contraseña del administrador
     * @return Nueva instancia de Administrador
     */
    public static Administrador crearAdministrador(String nombre, String email, String password) {
        // Aquí podríamos agregar lógica específica para admins:
        // - Verificar permisos
        // - Notificar a otros admins
        return new Administrador(nombre, email, password);
    }
    // ============================================
    
    /**
     * Método abstracto que cada tipo de usuario implementará
     * según sus necesidades específicas
     */
    public abstract void mostrarPanel();
    
    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
}
