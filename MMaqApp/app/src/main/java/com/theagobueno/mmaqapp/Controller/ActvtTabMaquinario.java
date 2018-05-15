package com.theagobueno.mmaqapp.Controller;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ListAdapter;
import android.widget.Toast;

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

public class ActvtTabMaquinario extends  Fragment {

    private ListView listView;
    private FloatingActionButton fab;
    private Maquinario m;
    List<String> maquinarioList = new ArrayList<>();
    List<String> listMarcaModelo = new ArrayList<>();
    List<String> listMaq = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.actvt_tab_maquinario, container, false);
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        listView = (ListView) rootView.findViewById(R.id.txtViewPdr);
        eventoListMaquinario();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {

                Bundle bundle = new Bundle();
                bundle.putString("id",listMaq.get(i));
                bundle.putString("marca",listMarcaModelo.get(i));
                Intent intent = new Intent(getActivity(), ActvtTabManutencao.class);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("id",listMaq.get(i));
                Intent intent = new Intent(getActivity(), DlgMaqui.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CadastroMaquinarioActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
    public void eventoListMaquinario(){
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.child("maquinario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                maquinarioList.clear();
                listMaq.clear();
                listMarcaModelo.clear();
                for (DataSnapshot objDataSnapshot:dataSnapshot.getChildren()){
                    m = objDataSnapshot.getValue(Maquinario.class);
                    maquinarioList.add(m.getMarca() +" /// "+ m.getModelo());
                    listMaq.add(m.getId());
                    listMarcaModelo.add(m.getMarca() + " /// " + m.getModelo() );
                }
                ArrayAdapter<String> maquinarioArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, maquinarioList);
                listView.setAdapter(maquinarioArrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}