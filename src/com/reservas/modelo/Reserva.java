package com.reservas.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Clase Reserva: representa una reserva de asientos para una función
 * 
 * PATRÓN OBSERVER INTEGRADO:
 * - Define la interfaz ObservadorReserva
 * - Define la clase NotificadorEmail como inner class
 * - Mantiene una lista de observadores
 * - Notifica automáticamente cuando cambia de estado
 */
public class Reserva {
    private String id;                              // ID único de la reserva
    private Cliente cliente;                        // Cliente que hizo la reserva
    private Funcion funcion;                        // Función reservada
    private List<Asiento> asientos;                 // Asientos reservados
    private EstadoReserva estado;                   // Estado actual (enum)
    private List<ObservadorReserva> observadores;   // Lista de observadores
    
    /**
     * Constructor que crea una reserva en estado PENDIENTE
     * @param cliente Cliente que realiza la reserva
     * @param funcion Función a reservar
     * @param asientos Lista de asientos seleccionados
     */
    public Reserva(Cliente cliente, Funcion funcion, List<Asiento> asientos) {
        this.id = UUID.randomUUID().toString();
        this.cliente = cliente;
        this.funcion = funcion;
        this.asientos = asientos;
        this.estado = EstadoReserva.PENDIENTE; // Estado inicial
        this.observadores = new ArrayList<>();
        
        // Auto-suscribimos el notificador de email por defecto
        // Cada reserva automáticamente enviará emails
        this.observadores.add(new NotificadorEmail());
    }
    
    // ============================================
    // PATRÓN OBSERVER
    // ============================================
    
    /**
     * Interfaz que define el contrato para los observadores
     * Cualquier clase que implemente esta interfaz puede
     * recibir notificaciones de cambios en la reserva
     */
    public interface ObservadorReserva {
        /**
         * Método que se ejecuta cuando la reserva cambia
         * @param reserva La reserva que cambió
         */
        void actualizar(Reserva reserva);
    }
    
    /**
     * Implementación concreta del observador: envío de emails
     * Es una clase estática interna (inner class) de Reserva
     * Tiene acceso directo a los datos de la reserva
     */
    public static class NotificadorEmail implements ObservadorReserva {
        /**
         * Envía un email con la información de la reserva
         * @param reserva Reserva sobre la cual notificar
         */
        @Override
        public void actualizar(Reserva reserva) {
            System.out.println("\n--- Notificación por Email ---");
            System.out.println("Para: " + reserva.getCliente().getEmail());
            System.out.println("Reserva ID: " + reserva.getId());
            System.out.println("Película: " + reserva.getFuncion().getPelicula().getTitulo());
            System.out.println("Estado: " + reserva.getEstado());
            System.out.println("Asientos: " + reserva.getAsientos().size());
            System.out.println("-----------------------------\n");
            
            // En una implementación real, aquí se usaría:
            // - Una librería como JavaMail
            // - Un servicio de email como SendGrid
            // - Un microservicio de notificaciones
        }
    }
    
    /**
     * Permite agregar nuevos observadores a la reserva
     * Por ejemplo: new NotificadorSMS(), new NotificadorWpp()
     * @param observador Observador a agregar
     */
    public void agregarObservador(ObservadorReserva observador) {
        observadores.add(observador);
    }
    
    /**
     * Método privado que notifica a TODOS los observadores
     * Se llama automáticamente cuando cambia el estado
     */
    private void notificarObservadores() {
        // Recorremos todos los observadores y llamamos su método actualizar()
        observadores.forEach(o -> o.actualizar(this));
    }
    // ============================================
    
    /**
     * Confirma la reserva:
     * 1. Cambia el estado a CONFIRMADA
     * 2. Marca los asientos como ocupados
     * 3. Notifica a todos los observadores
     */
    public void confirmar() {
        this.estado = EstadoReserva.CONFIRMADA;
        
        // Marcamos cada asiento como ocupado
        asientos.forEach(Asiento::reservar);
        
        // PATRÓN OBSERVER: notificamos el cambio de estado
        notificarObservadores();
    }
    
    /**
     * Cancela la reserva:
     * 1. Cambia el estado a CANCELADA
     * 2. Libera los asientos
     * 3. Notifica a todos los observadores
     */
    public void cancelar() {
        this.estado = EstadoReserva.CANCELADA;
        
        // Liberamos cada asiento
        asientos.forEach(Asiento::liberar);
        
        // PATRÓN OBSERVER: notificamos el cambio de estado
        notificarObservadores();
    }
    
    // Getters
    public String getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Funcion getFuncion() { return funcion; }
    public List<Asiento> getAsientos() { return asientos; }
    public EstadoReserva getEstado() { return estado; }
}