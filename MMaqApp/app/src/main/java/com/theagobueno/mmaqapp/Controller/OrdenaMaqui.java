package com.theagobueno.mmaqapp.Controller;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.theagobueno.mmaqapp.Entidades.Maquinario;
import com.theagobueno.mmaqapp.R;

import java.util.ArrayList;
import java.util.List;

public class OrdenaMaqui extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Button opcUm;
    private Button opcDois;
    private Button opcTres;
    private Button opcQuatro;
    private  Integer opcList = 0;
    private Integer select = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordena_maqui);

        opcUm = (Button) findViewById(R.id.opcUm);
        opcDois = (Button) findViewById(R.id.opcDois);
        opcTres = (Button) findViewById(R.id.opcTres);
        opcQuatro = (Button) findViewById(R.id.opcQuatro);

        opcUm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcList = 1;
                listaMaquinario(opcList);
            }
        });
        opcDois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcList = 2;
                listaMaquinario(opcList);
            }
        });
        opcTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcList = 3;
                listaMaquinario(opcList);
            }
        });
        opcQuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcList = 4;
                listaMaquinario(opcList);
            }
        });
    }

    public void listaMaquinario(Integer opcao){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        Intent intent = new Intent(OrdenaMaqui.this, MostraDados.class);
        Bundle bundle = new Bundle();
        switch (opcao){
            case 1:
                opcao = 1;
                bundle.putInt("opcao", opcao);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case 2:
                opcao = 2;
                bundle.putInt("opcao", opcao);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case 3:
                opcao = 3;
                bundle.putInt("opcao", opcao);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case 4:
                opcao = 4;
                bundle.putInt("opcao", opcao);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            default:
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle("Aviso");
                dlg.setMessage("Entrada inválida, opção não recebeu opçã válida");
                dlg.setNeutralButton("OK", null);
                dlg.show();
        }
    }


}
