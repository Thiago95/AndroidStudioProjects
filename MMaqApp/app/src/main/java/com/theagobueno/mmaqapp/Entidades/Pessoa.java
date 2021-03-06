package com.theagobueno.mmaqapp.Entidades;

/**
 * Created by thiag on 05/10/2017.
 */

public abstract class Pessoa {
    private String id;
    private String nome;
    private String enderco;
    private String email;
    private String senha;
    private String dataAdmissao;
    private String descricaoHabExp;
    private boolean cargo;
    private String cpf;
    private String nmrRegistroCNH;
    private String telefone;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDescricaoHabExp() {
        return descricaoHabExp;
    }

    public void setDescricaoHabExp(String descricaoHabExp) {
        this.descricaoHabExp = descricaoHabExp;
    }

    public String getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(String dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public boolean isCargo() {
        return cargo;
    }

    public void setCargo(boolean cargo) {
        this.cargo = cargo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNmrRegistroCNH() {
        return nmrRegistroCNH;
    }

    public void setNmrRegistroCNH(String nmrRegistroCNH) {
        this.nmrRegistroCNH = nmrRegistroCNH;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
