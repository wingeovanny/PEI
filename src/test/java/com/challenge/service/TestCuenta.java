package com.challenge.service;

import com.challenge.entity.Cuenta;
import com.challenge.global.TestGlobal;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TestCuenta extends TestGlobal {

    @Autowired
    private CuentaService cuentaService;

    @Test
    public void testFindOne() {

        long id = 100000;

        Cuenta cuenta = cuentaService.findOne(id);

        Assert.assertNotNull("Error - expected not null", cuenta);
        Assert.assertEquals("Error - expected id attribute match", id, cuenta.getId());

    }
}
