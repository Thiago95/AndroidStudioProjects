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

    private String id, marca, modelo, tipoMaquina, dataAquisicao;
    private int potencia, valorAquisicao;

    @Exclude

    public Map<String, Object> toMap(){
        HashMap<String, Object> hashMapMaquina = new HashMap<>();

        hashMapMaquina.put("id_maq", getId());
        hashMapMaquina.put("marca_maq", getMarca());
        hashMapMaquina.put("modelo_maq", getModelo());
        hashMapMaquina.put("tipoMaquina", getTipoMaquina());
        hashMapMaquina.put("potencia_maq", getPotencia());
        hashMapMaquina.put("valorAquisicao_maq", getValorAquisicao());
        hashMapMaquina.put("dataAquisicaod_maq", getDataAquisicao());

        return hashMapMaquina;

    }

    public int getValorAquisicao() {
        return valorAquisicao;
    }

    public void setValorAquisicao(int valorAquisicao) {
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

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public String getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(String dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }
}
