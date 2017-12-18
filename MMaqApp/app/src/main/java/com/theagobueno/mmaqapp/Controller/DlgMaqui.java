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
        int nmr = bundle.getInt("asd");
        long lng = bundle.getLong("long");
        String marca = bundle.getString("marca");
        String modelo = bundle.getString("modelo");
        int potencia = bundle.getInt("potencia");
        int valor = bundle.getInt("valor");
        listMaq.clear();
        listMaq.add(marca);
        listMaq.add(modelo);
        temp = ""+potencia;
        listMaq.add(temp);
        temp2 = ""+valor;
        listMaq.add(temp2);

        //ArrayList arrayList = bundle.getStringArrayList("list");
        ArrayAdapter<String> listMaqAdapter = new ArrayAdapter<String>(DlgMaqui.this, android.R.layout.simple_list_item_1, listMaq);
        listView.setAdapter(listMaqAdapter);

        TextView txtResultado = (TextView) findViewById(R.id.txtResultado);

        txtResultado.setText(nmr+" | "+marca);

       // eventoListMaquinario();



        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                inputDialog(i);
                return true;
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
