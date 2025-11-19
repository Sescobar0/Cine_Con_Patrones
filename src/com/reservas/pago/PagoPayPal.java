package com.reservas.pago;

/**
 * Estrategia concreta: Pago con PayPal
 * Implementa el algoritmo específico para procesar pagos vía PayPal
 */
public class PagoPayPal implements EstrategiaPago {
    private String email; // Email de la cuenta PayPal
    
    /**
     * Constructor que recibe el email de PayPal
     * @param email Correo asociado a la cuenta PayPal
     */
    public PagoPayPal(String email) {
        this.email = email;
    }
    
    /**
     * Implementación específica del pago con PayPal
     * @param monto Cantidad a cobrar
     * @return true si el pago es exitoso
     */
    @Override
    public boolean procesarPago(double monto) {
        System.out.println("Procesando pago de $" + monto + " con PayPal (" + email + ")");
        
        // En una implementación real, aquí se haría:
        // 1. Redirigir al usuario a PayPal
        // 2. Usuario se autentica en PayPal
        // 3. Usuario autoriza el pago
        // 4. PayPal redirige de vuelta con token
        // 5. Verificar el token con API de PayPal
        // 6. Completar la transacción
        
        return true; // Simulamos un pago exitoso
    }
}