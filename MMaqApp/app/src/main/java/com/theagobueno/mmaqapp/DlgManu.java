package com.theagobueno.mmaqapp;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.theagobueno.mmaqapp.Controller.ActvtTabManutencao;
import com.theagobueno.mmaqapp.Entidades.Manutencao;

import java.util.ArrayList;
import java.util.List;

public class DlgManu extends AppCompatActivity {
    Dialog d;
    List<String> manutencaoList = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dlg_manu);
        //Intent intent = getIntent();
       // Bundle bundle = intent.getExtras();

       // TextView txtResultado = (TextView) findViewById(R.id.txtResultado);

        eventoListManutencao();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                inputDialog(i);
                return true;
            }
        });
    }

    private void inputDialog(final int posicao) {
        d = new Dialog(DlgManu.this);
        d.setTitle("Editar Máquina");
        d.setContentView(R.layout.input_dialogo_manu);
        final EditText tipoManutencao = (EditText) d.findViewById(R.id.nome);
        final EditText dataManutencao = (EditText) d.findViewById(R.id.endereco);
        final EditText dataProximaManutencao = (EditText) d.findViewById(R.id.telefone);
        final EditText precoManutecao = (EditText) d.findViewById(R.id.cpf);
        final EditText descricacaoManutencao = (EditText) d.findViewById(R.id.cnh);


        Button updateBtn= (Button) d.findViewById(R.id.updateBtn);
        Button deleteBtn= (Button) d.findViewById(R.id.deleteBtn);
        if(posicao== -1) {
            updateBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
        }else {
            updateBtn.setEnabled(true);
            deleteBtn.setEnabled(true);
            tipoManutencao.setText(manutencaoList.get(posicao));
            dataManutencao.setText(manutencaoList.get(posicao+1));
            dataProximaManutencao.setText(manutencaoList.get(posicao+2));
            precoManutecao.setText(manutencaoList.get(posicao+3));
            descricacaoManutencao.setText(manutencaoList.get(posicao+4));

        }

        d.show();
    }

    public void eventoListManutencao(){
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.child("manutencao").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                manutencaoList.clear();
                String temp;
                for (DataSnapshot objDataSnapshot:dataSnapshot.getChildren()){
                    Manutencao m = objDataSnapshot.getValue(Manutencao.class);

                    manutencaoList.add(m.getTipoManutencao());
                    manutencaoList.add(m.getDataAtualManutencao());
                    manutencaoList.add(m.getDataProximaMatencao());
                    temp = ""+m.getCustoManutencao();
                    manutencaoList.add(temp);
                    manutencaoList.add(m.getDescriocaoManutencao());

                }
                ArrayAdapter<String> manutencaoArrayAdapter = new ArrayAdapter<String>(DlgManu.this, android.R.layout.simple_list_item_1, manutencaoList);
                listView.setAdapter(manutencaoArrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
