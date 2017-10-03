package com.theagobueno.mmaqsapp.Entidades;

import java.util.Date;

/**
 * Created by thiag on 01/10/2017.
 */

public class Maquinario {

    private String id, marca, modelo, tipoMaquina;
    private int  potencia, valorAquisicao;
    private Date dataAquisicao;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipoMaquina() {
        return tipoMaquina;
    }

    public void setTipoMaquina(String tipoMaquina) {
        this.tipoMaquina = tipoMaquina;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getValorAquisicao() {
        return valorAquisicao;
    }

    public void setValorAquisicao(int valorAquisicao) {
        this.valorAquisicao = valorAquisicao;
    }

    public Date getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(Date dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }
}
