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
    List<String> list = new ArrayList<>();
    List<String> listMaqui = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dlg_maqui);

        listView = (ListView) findViewById(R.id.txtViewPdr);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        final String id = bundle.getString("id");

        eventListDados(id);
        setListView(list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                inputDialog(listMaqui.get(i), i);
                /*Bundle bundle = new Bundle();
                bundle.putString("id",listMaqui.get(i));
                Intent intent = new Intent(DlgMaqui.this, DlgMaqui.class);
                intent.putExtras(bundle);
                startActivity(intent);*/
            }
        });



    }

    private void eventListDados(String id) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("maquinario").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Maquinario maqui = dataSnapshot.getValue(Maquinario.class);
                list.add("Marca: "+maqui.getMarca());
                list.add("Modelo: "+maqui.getModelo());
                list.add("Tipo: "+maqui.getTipoMaquina());
                list.add("Potência: "+String.valueOf(maqui.getPotencia()));
                list.add("Preço: "+maqui.getValorAquisicao());
                list.add("Data Compra: "+maqui.getDataAquisicao());
                listMaqui.add(maqui.getMarca());
                listMaqui.add(maqui.getModelo());
                listMaqui.add(maqui.getTipoMaquina());
                listMaqui.add(String.valueOf(maqui.getPotencia()));
                listMaqui.add(maqui.getValorAquisicao());
                listMaqui.add(maqui.getDataAquisicao());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void setListView(List<String> listMaqui) {
        ArrayAdapter<String> listMaqAdapter = new ArrayAdapter<String>(DlgMaqui.this, android.R.layout.simple_list_item_1, listMaqui);
        listView.setAdapter(listMaqAdapter);
    }

    private void inputDialog(String list, Integer posicao) {
        /*Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String marca = bundle.getString("marca");
        String modelo = bundle.getString("modelo");
        int potencia = bundle.getInt("potencia");
        int valor = bundle.getInt("valor");*/
        Dialog d = new Dialog(DlgMaqui.this);
        d.setTitle("Editar Máquina");
        d.setContentView(R.layout.input_dialogo);
        final EditText marcaText = (EditText) d.findViewById(R.id.edtCadMaqMarca);

        Button updateBtn= (Button) d.findViewById(R.id.updateBtn);
        Button deleteBtn= (Button) d.findViewById(R.id.deleteBtn);
        if(posicao== -1) {
            updateBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
        }else {
            updateBtn.setEnabled(true);
            deleteBtn.setEnabled(true);
            marcaText.setText(list);
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
