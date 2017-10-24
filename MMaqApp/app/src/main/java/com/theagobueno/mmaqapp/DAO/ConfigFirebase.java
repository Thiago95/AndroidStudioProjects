package com.theagobueno.mmaqapp.DAO;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.theagobueno.mmaqapp.Entidades.Maquinario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thiag on 19/09/2017.
 */

public class ConfigFirebase {
    public String userId;

    public static DatabaseReference referenceFiresebase;
    public static FirebaseAuth autenticacao;

    public ConfigFirebase() {
    }

    //TODO testando a conexao com o banco do firebase
    public static DatabaseReference getFirebase(){
        if (referenceFiresebase == null){
            referenceFiresebase = FirebaseDatabase.getInstance().getReference();
        }
        return referenceFiresebase;
    }

    //TODO testando a autenticacao com o firebase
    public static FirebaseAuth getFirebaseAutenticacao() {
        if (autenticacao == null) {
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }
}
