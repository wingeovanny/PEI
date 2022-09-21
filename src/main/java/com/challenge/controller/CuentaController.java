package com.challenge.controller;

import com.challenge.entity.Cuenta;
import com.challenge.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.Collection;

@RestController
@RequestMapping("/api/cuenta")
public class CuentaController extends BaseController {

    @Autowired
    private CuentaService cuentaService;

    // Consulta todas las cuentas
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Cuenta>> getAllAccounts() {
        Collection<Cuenta> accounts = cuentaService.findAll();
        if (accounts.isEmpty()) {
            throw new NoResultException("No existen cuentas");
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    // Metodo que permite deolver una cuenta por el ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cuenta> getAccount(@PathVariable("id") long id) {
        Cuenta cuenta = cuentaService.findOne(id);
        if (cuenta == null) {
            throw new NoResultException("Cuenta " + id + " no existe");
        }
        return new ResponseEntity<>(cuenta, HttpStatus.OK);
    }

    // Metodo que permite crear una cuenta
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cuenta> insert(@RequestBody Cuenta cuenta) {
        Cuenta ac = cuentaService.create(cuenta);
        if (ac == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(ac, HttpStatus.CREATED);
    }

    // Modifica una cuenta

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cuenta> update(@RequestBody Cuenta cuenta) {
        Cuenta a = cuentaService.findOne(cuenta.getId());
        if (a == null) {
            throw new NoResultException("Cuenta " + cuenta.getId() + " no existe");
        }
        Cuenta ac = cuentaService.update(cuenta);
        if (ac == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(ac, HttpStatus.OK);
    }

    // Elimina una cuenta con el ID

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cuenta> delete(@PathVariable("id") long id) {
        Cuenta a = cuentaService.findOne(id);
        if (a == null) {
            throw new NoResultException("Cuenta " + id + " no existe");
        }
        cuentaService.delete(id);
        return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);
    }
}
