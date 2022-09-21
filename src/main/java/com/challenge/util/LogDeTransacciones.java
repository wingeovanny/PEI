package com.challenge.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.challenge.entity.Transaccion;

public class LogDeTransacciones {

    private static final Logger logger = LogManager.getLogger("transaccion");

    // Log de las transacciones procesadas
    public static void log(Transaccion transaccion) {
        logger.info("Id Transaccion: {}, Estado: {}, Valor: {}, Cuenta Origen: {}, " +
                "Cuenta Destino: {},  TipoTransaccion: {}.",
                transaccion.getId(),
                transaccion.getEstadoTransaccion(),
                transaccion.getValor(),
                transaccion.getOrigen(),
                transaccion.getDestino(),
                transaccion.getTipoTransaccion());
    }
}
