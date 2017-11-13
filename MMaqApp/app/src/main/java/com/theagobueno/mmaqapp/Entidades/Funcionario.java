package com.theagobueno.mmaqapp.Entidades;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.theagobueno.mmaqapp.DAO.ConfigFirebase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by thiag on 05/10/2017.
 */

public class Funcionario extends Pessoa {

    public void salvar(){
        DatabaseReference refereceFirebase = ConfigFirebase.getFirebase();
        refereceFirebase.child("usuario").child(String.valueOf(getId())).setValue(this);
    }





    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public void setNome(String nome) {
        super.setNome(nome);
    }

    @Override
    public String getEnderco() {
        return super.getEnderco();
    }

    @Override
    public void setEnderco(String enderco) {
        super.setEnderco(enderco);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getSenha() {
        return super.getSenha();
    }

    @Override
    public void setSenha(String senha) {
        super.setSenha(senha);
    }

    @Override
    public String getDescricaoHabExp() {
        return super.getDescricaoHabExp();
    }

    @Override
    public void setDescricaoHabExp(String descricaoHabExp) {
        super.setDescricaoHabExp(descricaoHabExp);
    }

    @Override
    public boolean isCargo() {
        return super.isCargo();
    }

    @Override
    public void setCargo(boolean cargo) {
        super.setCargo(cargo);
    }

    @Override
    public int getCpf() {
        return super.getCpf();
    }

    @Override
    public void setCpf(int cpf) {
        super.setCpf(cpf);
    }

    @Override
    public int getNmrRegistroCNH() {
        return super.getNmrRegistroCNH();
    }

    @Override
    public void setNmrRegistroCNH(int nmrRegistroCNH) {
        super.setNmrRegistroCNH(nmrRegistroCNH);
    }

    @Override
    public String getDataAdmissao() {
        return super.getDataAdmissao();
    }

    @Override
    public void setDataAdmissao(String dataAdmissao) {
        super.setDataAdmissao(dataAdmissao);
    }

    @Override
    public int getTelefone() {
        return super.getTelefone();
    }

    @Override
    public void setTelefone(int telefone) {
        super.setTelefone(telefone);
    }
}
