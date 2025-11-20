
// NOTAS IMPORTANTES SOBRE EL DISEÑO:



1. SEPARACIÓN DE RESPONSABILIDADES
   - Cada clase tiene UNA responsabilidad clara
   - Usuario: gestión de usuarios
   - Reserva: lógica de reservas
   - Pago: procesamiento de pagos
   - Sistema: gestión centralizada

2. PRINCIPIO ABIERTO/CERRADO
   - Abierto para extensión (agregar nuevos pagos, notificaciones)
   - Cerrado para modificación (no tocamos código existente)

3. INVERSIÓN DE DEPENDENCIAS
   - Cliente depende de EstrategiaPago (interfaz), no de PagoTarjeta (implementación)
   - Reserva depende de ObservadorReserva (interfaz), no de NotificadorEmail

4. BAJO ACOPLAMIENTO
   - Las clases están débilmente acopladas
   - Cambios en un módulo no afectan a otros

5. ALTA COHESIÓN
   - Cada clase agrupa funcionalidades relacionadas
   - Factory está en Usuario porque crea usuarios
   - Observer está en Reserva porque notifica sobre reservas

6. FACILIDAD DE TESTING
   - Puedes crear mocks de EstrategiaPago para testing
   - Puedes crear mocks de ObservadorReserva
   - SistemaReservas puede resetearse entre tests

7. ESCALABILIDAD
   - Fácil agregar: nuevos usuarios, pagos, notificaciones
   - Fácil modificar: lógica de negocio está encapsulada
   - Fácil mantener: código bien organizado y documentado


   

// EJEMPLO DE EXTENSIÓN FUTURA: Nuevas estrategias de pago


/*
package com.reservas.pago;

// Es muy fácil agregar nuevos métodos de pago sin modificar código existente
// Solo necesitamos implementar la interfaz EstrategiaPago

public class PagoTransferencia implements EstrategiaPago {
    private String numeroCuenta;
    private String banco;
    
    public PagoTransferencia(String numeroCuenta, String banco) {
        this.numeroCuenta = numeroCuenta;
        this.banco = banco;
    }
    
    @Override
    public boolean procesarPago(double monto) {
        System.out.println("Procesando transferencia bancaria de $" + monto);
        System.out.println("Banco: " + banco);
        // Lógica específica de transferencia
        return true;
    }
}

public class PagoCriptomoneda implements EstrategiaPago {
    private String walletAddress;
    private String tipoMoneda; // BTC, ETH, etc.
    
    public PagoCriptomoneda(String walletAddress, String tipoMoneda) {
        this.walletAddress = walletAddress;
        this.tipoMoneda = tipoMoneda;
    }
    
    @Override
    public boolean procesarPago(double monto) {
        System.out.println("Procesando pago de $" + monto + " en " + tipoMoneda);
        // Lógica de blockchain
        return true;
    }
}

public class PagoEfectivo implements EstrategiaPago {
    private String codigoReferencia;
    
    public PagoEfectivo() {
        // Generamos un código de referencia único
        this.codigoReferencia = UUID.randomUUID().toString().substring(0, 8);
    }
    
    @Override
    public boolean procesarPago(double monto) {
        System.out.println("Pago en efectivo programado");
        System.out.println("Código de referencia: " + codigoReferencia);
        System.out.println("Monto a pagar: $" + monto);
        System.out.println("Pagar en taquilla antes de la función");
        return true; // Se confirma pero pago pendiente
    }
}
*/


// EJEMPLO DE EXTENSIÓN FUTURA: Nuevos observadores


/*
package com.reservas.modelo;

// Podemos agregar nuevos canales de notificación fácilmente

public class NotificadorSMS implements Reserva.ObservadorReserva {
    @Override
    public void actualizar(Reserva reserva) {
        System.out.println("\n--- Notificación por SMS ---");
        System.out.println("Enviando SMS al cliente...");
        System.out.println("Tu reserva " + reserva.getId() + " está " + reserva.getEstado());
        System.out.println("---------------------------\n");
        
        // En producción usarías Twilio, Vonage, o similar
    }
}

public class NotificadorPush implements Reserva.ObservadorReserva {
    @Override
    public void actualizar(Reserva reserva) {
        System.out.println("\n--- Notificación Push ---");
        System.out.println("Enviando notificación push a la app móvil...");
        System.out.println("Estado: " + reserva.getEstado());
        System.out.println("------------------------\n");
        
        // En producción usarías Firebase Cloud Messaging
    }
}

public class NotificadorWhatsApp implements Reserva.ObservadorReserva {
    @Override
    public void actualizar(Reserva reserva) {
        System.out.println("\n--- Notificación WhatsApp ---");
        System.out.println("Enviando mensaje de WhatsApp...");
        System.out.println("Reserva confirmada para: " + 
                         reserva.getFuncion().getPelicula().getTitulo());
        System.out.println("-----------------------------\n");
        
        // En producción usarías WhatsApp Business API
    }
}
*/
