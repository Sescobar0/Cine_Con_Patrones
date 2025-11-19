package com.reservas;

import com.reservas.modelo.*;
import com.reservas.pago.*;
import com.reservas.sistema.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal que demuestra el funcionamiento del sistema
 * Aquí se integran todos los patrones de diseño
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Sistema de Reservas de Películas ===\n");
        
        // ========================================
        // 1. PATRÓN SINGLETON
        // ========================================
        // Obtenemos la única instancia del sistema
        // No importa cuántas veces llamemos getInstance(), 
        // siempre será el mismo objeto
        SistemaReservas sistema = SistemaReservas.getInstance();
        
        // ========================================
        // 2. PATRÓN FACTORY METHOD
        // ========================================
        // Usamos el método factory estático de Usuario
        // En lugar de hacer: new Cliente(...)
        // Esto nos da más control sobre la creación
        Cliente cliente = Usuario.crearCliente("Juan Pérez", "juan@email.com", "pass123");
        System.out.println("Cliente creado: " + cliente.getNombre());
        
        // ========================================
        // 3. CONFIGURACIÓN DEL SISTEMA
        // ========================================
        // Creamos una película y la agregamos al sistema
        Pelicula pelicula = new Pelicula("Inception", "Sci-Fi", 148);
        sistema.agregarPelicula(pelicula);
        
        // Creamos una sala con 50 asientos
        Sala sala = new Sala(50);
        
        // Creamos una función (película en un horario y sala específicos)
        Funcion funcion = new Funcion(pelicula, LocalDateTime.now().plusDays(1), sala);
        sistema.agregarFuncion(funcion);
        
        System.out.println("\nPelícula disponible: " + pelicula.getTitulo());
        System.out.println("Horario: " + funcion.getHorario());
        
        // ========================================
        // 4. SELECCIÓN DE ASIENTOS
        // ========================================
        // El cliente selecciona los asientos que desea reservar
        List<Asiento> asientosSeleccionados = new ArrayList<>();
        asientosSeleccionados.add(sala.getAsientos().get(0)); // Asiento A1
        asientosSeleccionados.add(sala.getAsientos().get(1)); // Asiento A2
        
        System.out.println("\nAsientos seleccionados: A1, A2");
        
        // ========================================
        // 5. PATRÓN STRATEGY
        // ========================================
        // El cliente elige su método de pago
        // Podría ser PagoTarjeta, PagoPayPal, o cualquier otra estrategia
        // que implementemos en el futuro
        EstrategiaPago pago = new PagoTarjeta("1234-5678-9012-3456");
        
        // ========================================
        // 6. REALIZAR LA RESERVA
        // ========================================
        // Aquí se ejecuta la estrategia de pago seleccionada
        Reserva reserva = cliente.realizarReserva(funcion, asientosSeleccionados, pago);
        
        if (reserva != null) {
            // ========================================
            // 7. PATRÓN OBSERVER
            // ========================================
            // La reserva ya tiene un NotificadorEmail por defecto
            // Pero podríamos agregar más observadores:
            // reserva.agregarObservador(new NotificadorSMS());
            
            System.out.println("\n=== Confirmando reserva ===");
            
            // Al confirmar, se notifica automáticamente a todos los observadores
            reserva.confirmar();
            
            System.out.println("\nReserva completada exitosamente!");
            System.out.println("ID de reserva: " + reserva.getId());
        }
    }
}