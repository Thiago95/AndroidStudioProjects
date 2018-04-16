package com.theagobueno.mmaqapp.Entidades;

/**
 * Created by thiag on 10/10/2017.
 */

public class Manutencao {

    private String idManutencao;
    private String idMaquinario;
    private String tipoManutencao;
    private String dataAtualManutencao;
    private String dataProximaMatencao;
    private String custoManutencao;
    private String descriocaoManutencao;

    public String getIdManutencao() {
        return idManutencao;
    }

    public void setIdManutencao(String idManutencao) {
        this.idManutencao = idManutencao;
    }

    public String getIdMaquinario() {
        return idMaquinario;
    }

    public void setIdMaquinario(String idMaquinario) {
        this.idMaquinario = idMaquinario;
    }

    public String getTipoManutencao() {
        return tipoManutencao;
    }

    public void setTipoManutencao(String tipoManutencao) {
        this.tipoManutencao = tipoManutencao;
    }

    public String getDataAtualManutencao() {
        return dataAtualManutencao;
    }

    public void setDataAtualManutencao(String dataAtualManutencao) {
        this.dataAtualManutencao = dataAtualManutencao;
    }

    public String getDataProximaMatencao() {
        return dataProximaMatencao;
    }

    public void setDataProximaMatencao(String dataProximaMatencao) {
        this.dataProximaMatencao = dataProximaMatencao;
    }

    public String getCustoManutencao() {
        return custoManutencao;
    }

    public void setCustoManutencao(String custoManutencao) {
        this.custoManutencao = custoManutencao;
    }

    public String getDescriocaoManutencao() {
        return descriocaoManutencao;
    }

    public void setDescriocaoManutencao(String descriocaoManutencao) {
        this.descriocaoManutencao = descriocaoManutencao;
    }
}
