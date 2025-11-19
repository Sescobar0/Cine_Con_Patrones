package com.reservas.modelo;

/**
 * Enumeración que define los posibles estados de una reserva
 * Usar enum es mejor que usar Strings porque:
 * - Previene errores de tipeo
 * - El compilador verifica que solo uses valores válidos
 * - Más eficiente en memoria
 */
public enum EstadoReserva {
    PENDIENTE,      // Reserva creada pero aún no confirmada (pago en proceso)
    CONFIRMADA,     // Reserva pagada y confirmada
    CANCELADA       // Reserva cancelada por el usuario o el sistema
}
