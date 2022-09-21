package com.challenge.entity;

import javax.persistence.*;

import com.challenge.util.Constants.EstadoTransaccion;
import com.challenge.util.Constants.TipoTransaccion;

@Entity
public class Transaccion {

    @Id
    @GeneratedValue
    private long id;

    private long origen;
    private long destino;
    private long valor;
    private TipoTransaccion tipoTransaccion;
    private EstadoTransaccion estadoTransaccion;
    private int result;

    public Transaccion() {
    }

    public Transaccion(long origen, long destino, long valor) {
        this.origen = origen;
        this.destino = destino;
        this.valor = valor;

    }

    public long getId() {
        return id;
    }

    public int getResult() {
        return result;
    }

    public long getOrigen() {
        return origen;
    }

    public long getDestino() {
        return destino;
    }

    public long getValor() {
        return valor;
    }

    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }

    public EstadoTransaccion getEstadoTransaccion() {
        return estadoTransaccion;
    }

    public void setTipoTransaccion(TipoTransaccion tipo) {
        this.tipoTransaccion = tipo;
    }

    public void setEstadoTransaccion(EstadoTransaccion estado) {
        this.estadoTransaccion = estado;
    }

    public void setResult(int result) {
        this.result = result;
    }
}