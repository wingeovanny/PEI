package com.challenge;

import com.challenge.repository.CuentaRepositorio;
import com.challenge.entity.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CargaInicial implements ApplicationRunner {

    private CuentaRepositorio cuentaRepositorio;

    @Autowired
    public CargaInicial(CuentaRepositorio cuentaRepositorio) {
        this.cuentaRepositorio = cuentaRepositorio;
    }

    public void run(ApplicationArguments args) {
        cuentaRepositorio.save(new Cuenta("GYE", 50));
        cuentaRepositorio.save(new Cuenta("GYE", 60.80));
        cuentaRepositorio.save(new Cuenta("PEIGO", 100));
        cuentaRepositorio.save(new Cuenta("PEIGO", 400));
    }
}