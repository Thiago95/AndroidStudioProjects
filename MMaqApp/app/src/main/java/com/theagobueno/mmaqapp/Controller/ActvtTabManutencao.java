package com.theagobueno.mmaqapp.Controller;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.theagobueno.mmaqapp.Entidades.Manutencao;
import com.theagobueno.mmaqapp.R;

import java.util.ArrayList;
import java.util.List;

public class ActvtTabManutencao extends AppCompatActivity {

    private FloatingActionButton fab;
    List<String> manutencaoList = new ArrayList<>();
    private ListView listView;
    List<String> listMaq = new ArrayList<>();
    List<String> listMan = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvt_tab_manutencao);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String id = bundle.getString("id");
        final String marca = bundle.getString("marca");
        listView = (ListView) findViewById(R.id.txtViewPdr);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        selecionaMaqnutencao(id);
        ArrayAdapter<String> maquinarioArrayAdapter = new ArrayAdapter<String>(ActvtTabManutencao.this, android.R.layout.simple_list_item_1, manutencaoList);
        listView.setAdapter(maquinarioArrayAdapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActvtTabManutencao.this, CadastroManutencaoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",id);
                bundle.putString("marca",marca);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        /*listView = (ListView) findViewById(R.id.txtViewPdr);

        ArrayAdapter<String> listMaqAdapter = new ArrayAdapter<String>(ActvtTabManutencao.this, android.R.layout.simple_list_item_1, listMaq);
        listView.setAdapter(listMaqAdapter);
        //eventoListManutencao();
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
               /* Intent intent = new Intent(ActvtTabManutencao.this, DlgManu.class);
                Bundle bundle = new Bundle();

                intent.putExtras(bundle);

                startActivity(intent);
                Intent intent = new Intent(ActvtTabManutencao.this, DlgManu.class);
                startActivity(intent);
            }
        });*/

    }

    private void selecionaMaqnutencao(String i) {
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.child("maquinario").child(i).child("manutencao").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                manutencaoList.clear();
                listMan.clear();
                for (DataSnapshot objDataSnapshot:dataSnapshot.getChildren()){
                    Manutencao man = objDataSnapshot.getValue(Manutencao.class);

                    manutencaoList.add("--------------\n"+
                            "|Tipo de Manutenção: \n| - "+man.getTipoManutencao()+
                            "\n|Manutenção Realizada Dia: \n| - "+ man.getDataAtualManutencao() +
                            "\n|Próxima Manutenção Deste Tipo: \n| - "+ man.getDataProximaMatencao() +
                            "\n|Custo da Manutenção: \n| - "+ man.getCustoManutencao()+
                            "\n--------------");
                    listMan.add(man.getIdManutencao());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}