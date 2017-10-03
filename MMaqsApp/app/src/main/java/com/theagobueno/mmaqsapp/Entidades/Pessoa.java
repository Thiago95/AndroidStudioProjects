package com.theagobueno.mmaqsapp.Entidades;

import java.util.Date;

/**
 * Created by thiag on 01/10/2017.
 */

public abstract class Pessoa {

    private String id;
    private String nome;
    private String enderco;
    private String email;
    private String descricaoHabExp;
    private boolean cargo;
    private int cpf;
    private int nmrRegistroCNH;
    private Date dataAdmissao;
    private int telefone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEnderco() {
        return enderco;
    }

    public void setEnderco(String enderco) {
        this.enderco = enderco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricaoHabExp() {
        return descricaoHabExp;
    }

    public void setDescricaoHabExp(String descricaoHabExp) {
        this.descricaoHabExp = descricaoHabExp;
    }

    public boolean isCargo() {
        return cargo;
    }

    public void setCargo(boolean cargo) {
        this.cargo = cargo;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getNmrRegistroCNH() {
        return nmrRegistroCNH;
    }

    public void setNmrRegistroCNH(int nmrRegistroCNH) {
        this.nmrRegistroCNH = nmrRegistroCNH;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
}
