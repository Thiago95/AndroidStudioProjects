package com.theagobueno.prj_firebase.DAO;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by thiag on 16/09/2017.
 */

//TODO configurando o firebase
public class ConfigFirebase {
    public static DatabaseReference referenceFiresebase;
    public static FirebaseAuth autenticacao;

    //TODO testando a conexao com o banco do firebase
    public static DatabaseReference getFirebase(){
        if (referenceFiresebase == null){
            referenceFiresebase = FirebaseDatabase.getInstance().getReference();
        }
        return referenceFiresebase;
    }

    //TODO testando a autenticacao com o firebase
    public static FirebaseAuth getFirebaseAutenticacao(){
        if (autenticacao == null){
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }
}
