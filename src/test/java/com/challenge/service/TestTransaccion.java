package com.challenge.service;

import com.challenge.entity.Cuenta;
import com.challenge.entity.Transaccion;
import com.challenge.global.TestGlobal;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TestTransaccion extends TestGlobal {

    public static Cuenta cuentaunogye;
    public static Cuenta cuentadosgye;
    public static Cuenta cuentaunopeigo;
    public static Cuenta cuentadospeigo;

    @Autowired
    public TransaccionService transaccionService;

    @Autowired
    public CuentaService cuentaService;

    @Before
    public void cargaCuentas() {
        cuentaunogye = cuentaService.create(new Cuenta("GYE", 10000));
        cuentadosgye = cuentaService.create(new Cuenta("GYE", 10000));
        cuentaunopeigo = cuentaService.create(new Cuenta("PEIGO", 10000));
        cuentadospeigo = cuentaService.create(new Cuenta("PEIGO", 10000));
    }

    @After
    public void eliminaCuentas() {
        cuentaService.delete(cuentaunogye.getId());
        cuentaService.delete(cuentadosgye.getId());
        cuentaService.delete(cuentaunopeigo.getId());
        cuentaService.delete(cuentadospeigo.getId());
    }

    @Test
    public void transacccion() {

        long valorTransaccion = 100;

        Transaccion newTransaction = new Transaccion(
                cuentaunogye.getId(),
                cuentadosgye.getId(),
                valorTransaccion);

        Transaccion resultadoTransaccion = transaccionService.process(newTransaction);

        String msg = "Error en el test de la transaccion";

        Assert.assertEquals(msg, resultadoTransaccion.getId(), 1, 0);
    }

}
