package com.challenge.controller;

import com.challenge.entity.Transaccion;
import com.challenge.service.AsyncTransaction;
import com.challenge.service.TransaccionService;
import com.challenge.util.Constants.EstadoTransaccion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/api/transaccion")
public class TransaccionController extends BaseController {

    @Autowired
    private TransaccionService transaccionService;

    @Autowired
    private ThreadPoolTaskExecutor transactionPool;

    // Retorna una lista de transacciones
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Transaccion>> findAll() {
        Collection<Transaccion> t = transaccionService.findAll();
        if (t.isEmpty()) {
            throw new NoResultException("No existen transacciones");
        }
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    // Obtiene el detalle de una transaccion por su ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaccion> find(@PathVariable("id") long id) {
        Transaccion t = transaccionService.find(id);
        if (t == null) {
            throw new NoResultException("Transaction " + id + " no existe");
        }
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    // Procesar una transaccion y valida que la cuenta tenga saldo o que no permita
    // realizar
    // entre la misma cuenta
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaccion> insert(@RequestBody Transaccion transaction) {
        AsyncTransaction asyncTransaction;
        Future<Transaccion> asyncResponse;
        try {
            asyncTransaction = new AsyncTransaction(transaction);
            asyncTransaction.setTransactionService(transaccionService);
            asyncResponse = transactionPool.submit(asyncTransaction);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            if (asyncResponse.get().getEstadoTransaccion() == EstadoTransaccion.ERROR_CUENTA_NO_EXISTE) {
                return new ResponseEntity<>(asyncResponse.get(), HttpStatus.NOT_FOUND);
            } else if (asyncResponse.get().getEstadoTransaccion() == EstadoTransaccion.ERROR_MISMA_CUENTA) {
                return new ResponseEntity<>(asyncResponse.get(), HttpStatus.UNPROCESSABLE_ENTITY);
            } else if (asyncResponse.get().getEstadoTransaccion() == EstadoTransaccion.ERROR_SALDO_INSUFICIENTE) {
                return new ResponseEntity<>(asyncResponse.get(), HttpStatus.UNPROCESSABLE_ENTITY);
            } else {
                return new ResponseEntity<>(asyncResponse.get(), HttpStatus.CREATED);
            }

        } catch (InterruptedException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
