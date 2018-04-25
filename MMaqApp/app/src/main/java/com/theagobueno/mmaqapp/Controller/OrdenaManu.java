package com.theagobueno.mmaqapp.Controller;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.theagobueno.mmaqapp.R;

public class OrdenaManu extends AppCompatActivity {

    private Button opcDez;
    private Button opcOnze;
    private Button opcDoze;
    private Button opcTreze;
    private  Integer opcList = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordena_manu);
        opcDez = (Button) findViewById(R.id.opcDez);
        opcOnze = (Button) findViewById(R.id.opcOnze);
        opcDoze = (Button) findViewById(R.id.opcDoze);
        opcTreze = (Button) findViewById(R.id.opcTreze);

        opcDez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcList = 10;
                listaManutencao(opcList);
            }
        });
        opcOnze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcList = 11;
                listaManutencao(opcList);
            }
        });
        opcDoze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcList = 12;
                listaManutencao(opcList);
            }
        });
        opcTreze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcList = 13;
                listaManutencao(opcList);
            }
        });
    }

    private void listaManutencao(Integer opcao) {
        Intent intent = new Intent(OrdenaManu.this, MostraDados.class);
        Bundle bundle = new Bundle();
        switch (opcao){
            case 10:
                opcao = 10;
                bundle.putInt("opcao", opcao);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case 11:
                opcao = 11;
                bundle.putInt("opcao", opcao);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case 12:
                opcao = 12;
                bundle.putInt("opcao", opcao);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case 13:
                opcao = 13;
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
