package com.challenge.util;

public class Constants {

    public enum EstadoTransaccion {
        OK,
        ERROR_MISMA_CUENTA,
        ERROR_CUENTA_NO_EXISTE,
        ERROR_SALDO_INSUFICIENTE
    }

    public enum TipoTransaccion {
        PAGO,
        ABONO
    }

}
