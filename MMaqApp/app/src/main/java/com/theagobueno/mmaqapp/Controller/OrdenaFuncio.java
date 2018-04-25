package com.theagobueno.mmaqapp.Controller;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.theagobueno.mmaqapp.Entidades.Funcionario;
import com.theagobueno.mmaqapp.R;

import java.util.ArrayList;
import java.util.List;

public class OrdenaFuncio extends AppCompatActivity {

    private Button opcCinco;
    private Button opcSeis;
    private Button opcSete;
    private Button opcOito;
    private Button opcNove;
    private  Integer opcList = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordena_funcio);
        opcCinco = (Button) findViewById(R.id.opcCinco);
        opcSeis = (Button) findViewById(R.id.opcSeis);
        opcSete = (Button) findViewById(R.id.opcSete);
        opcOito = (Button) findViewById(R.id.opcOito);
        opcNove = (Button) findViewById(R.id.opcNove);

        opcCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcList = 5;
                listaFuncionario(opcList);
            }
        });
        opcSeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcList = 6;
                listaFuncionario(opcList);
            }
        });
        opcSete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcList = 7;
                listaFuncionario(opcList);
            }
        });
        opcOito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcList = 8;
                listaFuncionario(opcList);
            }
        });
        opcNove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcList = 9;
                listaFuncionario(opcList);
            }
        });
    }

    private void listaFuncionario(Integer opcao) {
        Intent intent = new Intent(OrdenaFuncio.this, MostraDados.class);
        Bundle bundle = new Bundle();
        switch (opcao){
            case 5:
                opcao = 5;
                bundle.putInt("opcao", opcao);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case 6:
                opcao = 6;
                bundle.putInt("opcao", opcao);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case 7:
                opcao = 7;
                bundle.putInt("opcao", opcao);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case 8:
                opcao = 8;
                bundle.putInt("opcao", opcao);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case 9:
                opcao = 9;
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
