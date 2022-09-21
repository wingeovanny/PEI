package com.challenge.service;

import java.util.concurrent.Callable;

import com.challenge.entity.Transaccion;

public class AsyncTransaction implements Callable<Transaccion> {

    Transaccion transaction;

    private TransaccionService transaccionService;

    public AsyncTransaction(Transaccion transaction) {
        this.transaction = transaction;
    }

    public void setTransactionService(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @Override
    public Transaccion call() throws Exception {
        return process(transaction);
    }

    /**
     * Procesamiento de la transaccion en el servicio
     *
     * @param transaction
     * @return transaction
     */
    public Transaccion process(Transaccion transaction) {
        return transaccionService.process(transaction);
    }

}
