package com.theagobueno.prj_firebase.Helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by thiag on 18/09/2017.
 */
//TODO essa classe é para salvar quando o usuario conectar no seu celular ou nao
public class Preferencias {
    private Context context;
    private SharedPreferences preferences;
    private String NOME_ARQUIVO;
    private int MODE = 0;
    private SharedPreferences.Editor editor;

    private final String CHAVE_IDENTIFICADOR = "identificarUsuarioLogado";
    private final String CHAVE_NOME = "nomeUsuarioLogado";

    public Preferencias(Context context){
        this.context = context;
        preferences = context.getSharedPreferences(NOME_ARQUIVO, MODE);

        editor = preferences.edit();
    }

    public void salvarUsuarioPreferencias(String identicadorUsuario, String nomeUsuario){
        editor.putString(CHAVE_IDENTIFICADOR, identicadorUsuario);
        editor.putString(CHAVE_NOME, nomeUsuario);
        editor.commit();
    }

    public String getIdentificador(){
        return preferences.getString(CHAVE_IDENTIFICADOR, null);
    }

    public String getNome(){
        return preferences.getString(CHAVE_NOME, null);
    }

}
