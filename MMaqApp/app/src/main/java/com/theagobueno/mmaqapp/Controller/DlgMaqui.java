package com.theagobueno.mmaqapp.Controller;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import com.theagobueno.mmaqapp.Entidades.Maquinario;
import com.theagobueno.mmaqapp.Helper.EstadoApp;
import com.theagobueno.mmaqapp.R;

import java.util.ArrayList;
import java.util.List;

public class DlgMaqui extends AppCompatActivity {

    private ListView listView;
    private Dialog d;
    private Maquinario m;
    List<String> maquinarioList = new ArrayList<>();
    List<String> listMaq = new ArrayList<>();
    String temp;
    String temp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dlg_maqui);
        listView = (ListView) findViewById(R.id.txtViewPdr);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String id = bundle.getString("id");
        eventoListMaquinario(id);

        //TextView txtResultado = (TextView) findViewById(R.id.txtResultado);

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                inputDialog(i);
            }
        });*/


    }

    private void eventoListMaquinario(String i) {

        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.child("maquinario").child(i).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                maquinarioList.clear();
                for (DataSnapshot objDataSnapshot:dataSnapshot.getChildren()){
                    m = objDataSnapshot.getValue(Maquinario.class);
                    maquinarioList.add(m.getMarca());
                    maquinarioList.add(m.getModelo());
                    maquinarioList.add(m.getTipoMaquina());
                    maquinarioList.add(String.valueOf(m.getPotencia()));
                    maquinarioList.add(m.getValorAquisicao());
                    maquinarioList.add(m.getDataAquisicao());
                }
                ArrayAdapter<String> listMaqAdapter = new ArrayAdapter<String>(DlgMaqui.this, android.R.layout.simple_list_item_1, maquinarioList);
                listView.setAdapter(listMaqAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void inputDialog(final int posicao) {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String marca = bundle.getString("marca");
        String modelo = bundle.getString("modelo");
        int potencia = bundle.getInt("potencia");
        int valor = bundle.getInt("valor");
        d = new Dialog(DlgMaqui.this);
        d.setTitle("Editar Máquina");
        d.setContentView(R.layout.input_dialogo);
        final EditText marcaText = (EditText) d.findViewById(R.id.edtCadMaqMarca);
        final EditText modeloText = (EditText) d.findViewById(R.id.edtCadMaqModelo);
        final EditText potenciaText = (EditText) d.findViewById(R.id.edtCadMaqPotencia);
        final EditText valorText = (EditText) d.findViewById(R.id.edtCadMaqValor);
        Button updateBtn= (Button) d.findViewById(R.id.updateBtn);
        Button deleteBtn= (Button) d.findViewById(R.id.deleteBtn);
        if(posicao== -1) {
            updateBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
        }else {
            updateBtn.setEnabled(true);
            deleteBtn.setEnabled(true);
            marcaText.setText(marca);
            modeloText.setText(modelo);
            potenciaText.setText(""+potencia);
            valorText.setText(""+valor);
        }

        d.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusair, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

       if(id == R.id.menuSair){
            SharedPreferences myPrefs = getSharedPreferences("MY", MODE_PRIVATE);
            SharedPreferences.Editor editor = myPrefs.edit();
            editor.clear();
            editor.commit();
            EstadoApp.getSingleInstance().setLoggingOut(true);
            Intent intent = new Intent(DlgMaqui.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();


        }
        return true;
    }
}
