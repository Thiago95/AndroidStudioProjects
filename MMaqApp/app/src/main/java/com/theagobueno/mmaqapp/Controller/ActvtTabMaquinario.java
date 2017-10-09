package com.theagobueno.mmaqapp.Controller;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.theagobueno.mmaqapp.Controller.CadastroMaquinarioActivity;
import com.theagobueno.mmaqapp.Entidades.Maquinario;
import com.theagobueno.mmaqapp.R;

import java.util.ArrayList;
import java.util.List;

public class ActvtTabMaquinario extends  Fragment {
    private static final String TAG = "ActvtTabMaquinario";

    private ListView listView;
    private FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.actvt_tab_maquinario, container, false);
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        listView = (ListView) rootView.findViewById(R.id.txtViewPdr);
        eventoListMaquinario();

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
                List<String> maquinarioList = new ArrayList<>();
                //ArrayAdapter<String> maquinarioArrayAdapter;
                maquinarioList.clear();
                for (DataSnapshot objDataSnapshot:dataSnapshot.getChildren()){
                    Maquinario m = objDataSnapshot.getValue(Maquinario.class);
                    maquinarioList.add(m.getMarca() +" /// "+ m.getModelo());

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
