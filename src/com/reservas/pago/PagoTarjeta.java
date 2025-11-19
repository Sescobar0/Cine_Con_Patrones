package com.reservas.pago;

/**
 * Estrategia concreta: Pago con Tarjeta de Crédito/Débito
 * Implementa el algoritmo específico para procesar pagos con tarjeta
 */
public class PagoTarjeta implements EstrategiaPago {
    private String numeroTarjeta; // Número de tarjeta (debería estar encriptado)
    
    /**
     * Constructor que recibe los datos de la tarjeta
     * @param numeroTarjeta Número de la tarjeta
     */
    public PagoTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }
    
    /**
     * Implementación específica del pago con tarjeta
     * @param monto Cantidad a cobrar
     * @return true si el pago es exitoso
     */
    @Override
    public boolean procesarPago(double monto) {
        // Mostramos solo los últimos 4 dígitos por seguridad
        System.out.println("Procesando pago de $" + monto + " con tarjeta ****" + 
                         numeroTarjeta.substring(numeroTarjeta.length() - 4));
        
        // En una implementación real, aquí se haría:
        // 1. Validar el número de tarjeta (algoritmo de Luhn)
        // 2. Verificar fecha de expiración
        // 3. Validar CVV
        // 4. Conectar con pasarela de pago (Stripe, PayU, etc.)
        // 5. Procesar la transacción
        // 6. Manejar respuestas de aprobación/rechazo
        
        return true; // Simulamos un pago exitoso
    }
}