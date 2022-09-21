package com.challenge.service;

import com.challenge.entity.Cuenta;
import com.challenge.entity.Transaccion;
import com.challenge.repository.CuentaRepositorio;
import com.challenge.repository.TransaccionRepositorio;
import com.challenge.util.LogDeTransacciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import static com.challenge.util.Constants.*;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepositorio transactionRepository;

    @Autowired
    private CuentaRepositorio cuentaRepositorio;

    public Collection<Transaccion> findAll() {
        return transactionRepository.findAll();
    }

    public Transaccion find(long id) {
        return transactionRepository.findOne(id);
    }

    // Procesamiento de la transaccion
    public Transaccion process(Transaccion transaccion) {

        TipoTransaccion tipo = (transaccion.getTipoTransaccion());
        transaccion.setTipoTransaccion(tipo);

        EstadoTransaccion status = validaEstadoTransaccion(transaccion);
        transaccion.setEstadoTransaccion(status);

        if (status == EstadoTransaccion.OK) {
            save(transaccion);
        }
        LogDeTransacciones.log(transaccion);
        return transaccion;
    }

    // Una vez que se hicieron las validaciones se procesa la transaccon, guarda y
    // loguea de la transaccion,

    public Transaccion save(Transaccion transaccion) {

        Cuenta origen = cuentaRepositorio.findOne(transaccion.getOrigen());
        Cuenta destino = cuentaRepositorio.findOne(transaccion.getDestino());

        double totalSaldo = transaccion.getValor();
        double saldoOrigenActual = origen.getSaldo();
        double saldoDestinoActual = destino.getSaldo();

        // Actualizo los salgos de las cuentas de origen y destino
        origen.setSaldo(saldoOrigenActual - totalSaldo);
        destino.setSaldo(saldoDestinoActual + totalSaldo);

        cuentaRepositorio.save(origen);
        cuentaRepositorio.save(destino);

        return transactionRepository.save(transaccion);
    }

    public EstadoTransaccion validaEstadoTransaccion(Transaccion transaccion) {

        if (transaccion.getOrigen() == transaccion.getDestino())
            return EstadoTransaccion.ERROR_MISMA_CUENTA;

        Cuenta origen = cuentaRepositorio.findOne(transaccion.getOrigen());
        if (origen == null)
            return EstadoTransaccion.ERROR_CUENTA_NO_EXISTE;

        Cuenta destino = cuentaRepositorio.findOne(transaccion.getDestino());
        if (destino == null)
            return EstadoTransaccion.ERROR_CUENTA_NO_EXISTE;

        double resultSourceSaldo = origen.getSaldo() - transaccion.getValor();
        if (resultSourceSaldo < 0)
            return EstadoTransaccion.ERROR_SALDO_INSUFICIENTE;

        return EstadoTransaccion.OK;
    }

}
