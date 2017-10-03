package com.theagobueno.mmaqsapp.Entidades;

import java.util.Date;

/**
 * Created by thiag on 02/10/2017.
 */

public class Manutencao {

    private String idManutencao;
    private String idMaquinario;
    private String idFuncionario;
    private String tipoManutencao;
    private String descricaoManutecao;
    private Date dataManutencaoRealizada;
    private Date dataProximaManutencao;
    private int valorManutencao;

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

    public String getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(String idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getTipoManutencao() {
        return tipoManutencao;
    }

    public void setTipoManutencao(String tipoManutencao) {
        this.tipoManutencao = tipoManutencao;
    }

    public String getDescricaoManutecao() {
        return descricaoManutecao;
    }

    public void setDescricaoManutecao(String descricaoManutecao) {
        this.descricaoManutecao = descricaoManutecao;
    }

    public Date getDataManutencaoRealizada() {
        return dataManutencaoRealizada;
    }

    public void setDataManutencaoRealizada(Date dataManutencaoRealizada) {
        this.dataManutencaoRealizada = dataManutencaoRealizada;
    }

    public Date getDataProximaManutencao() {
        return dataProximaManutencao;
    }

    public void setDataProximaManutencao(Date dataProximaManutencao) {
        this.dataProximaManutencao = dataProximaManutencao;
    }

    public int getValorManutencao() {
        return valorManutencao;
    }

    public void setValorManutencao(int valorManutencao) {
        this.valorManutencao = valorManutencao;
    }
}
