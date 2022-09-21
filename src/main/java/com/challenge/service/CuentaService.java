package com.challenge.service;

import com.challenge.repository.CuentaRepositorio;
import com.challenge.entity.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepositorio cuentaRepositorio;

    public Collection<Cuenta> findAll() {
        return cuentaRepositorio.findAll();
    }

    public Cuenta findOne(long id) {
        return cuentaRepositorio.findOne(id);
    }

    public Cuenta create(Cuenta cuenta) {
        return cuentaRepositorio.save(cuenta);
    }

    public Cuenta update(Cuenta cuenta) {
        return cuentaRepositorio.save(cuenta);
    }

    public Cuenta delete(long id) {
        Cuenta cuenta = cuentaRepositorio.findOne(id);
        cuentaRepositorio.delete(id);
        return cuenta;
    }

}
