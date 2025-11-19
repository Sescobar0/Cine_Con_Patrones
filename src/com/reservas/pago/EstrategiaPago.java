package com.reservas.pago;

/**
 * PATRÓN STRATEGY
 * 
 * Interfaz que define el contrato para todas las estrategias de pago
 * Permite cambiar el algoritmo de pago en tiempo de ejecución
 * 
 * Ventajas:
 * - Cada método de pago encapsula su propia lógica
 * - Fácil agregar nuevos métodos sin modificar código existente
 * - El cliente (Reserva) no necesita conocer los detalles de cada pago
 */
public interface EstrategiaPago {
    /**
     * Procesa un pago con el método específico
     * @param monto Cantidad a pagar
     * @return true si el pago fue exitoso, false si falló
     */
    boolean procesarPago(double monto);
}