package com.theagobueno.mmaqapp.Entidades;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.theagobueno.mmaqapp.DAO.ConfigFirebase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thiag on 19/09/2017.
 */

public class Maquinario {

    private String id, marca, modelo, tipoMaquina, dataAquisicao,valorAquisicao;
    private Integer potencia;
    //private Double valorAquisicao;

    public void salvar(){
        DatabaseReference refereceFirebase = ConfigFirebase.getFirebase();
        refereceFirebase.child("maquinario").child(getId()).setValue(this);    }

    public String getValorAquisicao() {
        return valorAquisicao;
    }

    public void setValorAquisicao(String valorAquisicao) {
        this.valorAquisicao = valorAquisicao;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPotencia() {
        return potencia;
    }

    public void setPotencia(Integer potencia) {
        this.potencia = potencia;
    }

    public String getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(String dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }
}
