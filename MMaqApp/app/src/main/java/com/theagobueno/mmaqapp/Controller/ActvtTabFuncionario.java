package com.theagobueno.mmaqapp.Controller;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.theagobueno.mmaqapp.Entidades.Funcionario;
import com.theagobueno.mmaqapp.R;

import java.util.ArrayList;
import java.util.List;

public class ActvtTabFuncionario extends Fragment {

    private FloatingActionButton fab;

    private ListView listView;
    private Funcionario f;
    List<String> listFun = new ArrayList<>();
    List<String> funcioraioList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.actvt_tab_funcionario, container, false);
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        listView = (ListView) rootView.findViewById(R.id.txtViewPdr);
        eventoListFuncionario();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), DlgFunc.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",listFun.get(i));
                bundle.putString("nome", funcioraioList.get(i));
                intent.putExtras(bundle);

                startActivity(intent);
                //Intent intent = new Intent(getActivity(), DlgFunc.class);
                //startActivity(intent);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CadastroFuncionarioActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    public void eventoListFuncionario(){
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.child("usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                funcioraioList.clear();
                listFun.clear();
                for (DataSnapshot objDataSnapshot:dataSnapshot.getChildren()){
                    f = objDataSnapshot.getValue(Funcionario.class);

                    funcioraioList.add(f.getNome());
                    listFun.add(f.getId());

                }
                ArrayAdapter<String> funcionarioArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, funcioraioList);
                listView.setAdapter(funcionarioArrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
