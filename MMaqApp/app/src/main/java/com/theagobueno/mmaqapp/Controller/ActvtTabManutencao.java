package com.theagobueno.mmaqapp.Controller;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.theagobueno.mmaqapp.Entidades.Manutencao;
import com.theagobueno.mmaqapp.Entidades.Maquinario;
import com.theagobueno.mmaqapp.R;

import java.util.ArrayList;
import java.util.List;

public class ActvtTabManutencao extends AppCompatActivity {

    private FloatingActionButton fab;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvt_tab_manutencao);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        listView = (ListView) findViewById(R.id.txtViewPdr);
        eventoListManutencao();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActvtTabManutencao.this, CadastroManutencaoActivity.class);
                startActivity(intent);
            }
        });
    }

    public void eventoListManutencao(){
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.child("maquinario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> maquinarioList = new ArrayList<>();
                maquinarioList.clear();
                for (DataSnapshot objDataSnapshot:dataSnapshot.getChildren()){
                    Maquinario m = objDataSnapshot.getValue(Maquinario.class);
                    maquinarioList.add(m.getId()+m.getModelo()+m.getMarca()+m.getTipoMaquina()+m.getPotencia()+m.getValorAquisicao()+m.getDataAquisicao());

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.child("manutencao").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> manutencaoList = new ArrayList<>();
                manutencaoList.clear();
                for (DataSnapshot objDataSnapshot:dataSnapshot.getChildren()){
                    Manutencao m = objDataSnapshot.getValue(Manutencao.class);
                    
                    manutencaoList.add(m.getDataAtualManutencao() +" - "+ m.getCustoManutencao()+" - "+m.getIdFuncionario());

                }
                ArrayAdapter<String> maquinarioArrayAdapter = new ArrayAdapter<String>(ActvtTabManutencao.this, android.R.layout.simple_list_item_1, manutencaoList);
                listView.setAdapter(maquinarioArrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}