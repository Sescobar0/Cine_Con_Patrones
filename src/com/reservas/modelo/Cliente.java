package com.reservas.modelo;

import com.reservas.pago.EstrategiaPago;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Cliente: representa a un usuario que puede hacer reservas
 * Extiende de Usuario e implementa funcionalidades específicas
 */
public class Cliente extends Usuario {
    // Lista de todas las reservas realizadas por este cliente
    private List<Reserva> reservas;
    
    /**
     * Constructor público que inicializa un cliente
     * @param nombre Nombre del cliente
     * @param email Email del cliente
     * @param password Contraseña del cliente
     */
    public Cliente(String nombre, String email, String password) {
        super(nombre, email, password); // Llama al constructor de Usuario
        this.reservas = new ArrayList<>(); // Inicializa la lista vacía
    }
    
    /**
     * Implementación específica del panel para clientes
     */
    @Override
    public void mostrarPanel() {
        System.out.println("=== Panel de Cliente ===");
        System.out.println("Bienvenido, " + nombre);
    }
    
    /**
     * Método principal para realizar una reserva
     * Aquí se integra el PATRÓN STRATEGY para el pago
     * 
     * @param funcion La función de cine a reservar
     * @param asientos Lista de asientos seleccionados
     * @param pago Estrategia de pago elegida (tarjeta, PayPal, etc.)
     * @return La reserva creada o null si falla
     */
    public Reserva realizarReserva(Funcion funcion, List<Asiento> asientos, EstrategiaPago pago) {
        // 1. Verificar que los asientos estén disponibles
        if (!funcion.verificarDisponibilidad(asientos)) {
            System.out.println("Los asientos seleccionados no están disponibles");
            return null; // Reserva fallida
        }
        
        // 2. Crear la reserva (aún en estado PENDIENTE)
        Reserva reserva = new Reserva(this, funcion, asientos);
        
        // 3. Calcular el monto total (precio fijo de $10 por asiento)
        double monto = asientos.size() * 10.0;
        
        // 4. PATRÓN STRATEGY: ejecutar el algoritmo de pago seleccionado
        // No nos importa CÓMO procesa el pago, solo que implemente procesarPago()
        if (pago.procesarPago(monto)) {
            // 5. Si el pago es exitoso, agregamos la reserva a la lista del cliente
            reservas.add(reserva);
            return reserva; // Retornamos la reserva para que pueda ser confirmada
        }
        
        return null; // Pago fallido
    }
    
    /**
     * Permite al cliente consultar todas sus reservas
     * @return Copia de la lista de reservas (para evitar modificaciones externas)
     */
    public List<Reserva> consultarReservas() {
        return new ArrayList<>(reservas);
    }
}
