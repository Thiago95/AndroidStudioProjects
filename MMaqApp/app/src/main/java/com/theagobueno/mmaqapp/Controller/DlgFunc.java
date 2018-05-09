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
import com.theagobueno.mmaqapp.Entidades.Funcionario;
import com.theagobueno.mmaqapp.Entidades.Maquinario;
import com.theagobueno.mmaqapp.Helper.EstadoApp;
import com.theagobueno.mmaqapp.R;

import java.util.ArrayList;
import java.util.List;

public class DlgFunc extends AppCompatActivity {

    private ListView listView;
    private Dialog d;
    private Funcionario f;
    List<String> funcionarioList = new ArrayList<>();
    List<String> fincioraioList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dlg_func);
        listView = (ListView) findViewById(R.id.txtViewPdr);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String id = bundle.getString("id");
        final String nome = bundle.getString("nome");
        TextView txtResultado = (TextView) findViewById(R.id.txtResultado);
        txtResultado.setText(nome);
        eventoListFuncionario(id);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                inputDialog(i);
                return true;
            }
        });


    }

    private void inputDialog(final int posicao) {
        d = new Dialog(DlgFunc.this);
        d.setTitle("Editar Máquina");
        d.setContentView(R.layout.input_dialogo_func);
        final EditText nome = (EditText) d.findViewById(R.id.nome);
        final EditText endereco = (EditText) d.findViewById(R.id.endereco);
        final EditText telefone = (EditText) d.findViewById(R.id.telefone);
        final EditText cpf = (EditText) d.findViewById(R.id.cpf);
        final EditText cnh = (EditText) d.findViewById(R.id.cnh);
        final EditText dataAdmissao = (EditText) d.findViewById(R.id.dataAdmissao);
        final EditText descricao = (EditText) d.findViewById(R.id.descri);

        Button updateBtn= (Button) d.findViewById(R.id.updateBtn);
        Button deleteBtn= (Button) d.findViewById(R.id.deleteBtn);
        if(posicao== -1) {
            updateBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
        }else {
            updateBtn.setEnabled(true);
            deleteBtn.setEnabled(true);
            nome.setText(fincioraioList.get(posicao));
            endereco.setText(fincioraioList.get(posicao+1));
            telefone.setText(fincioraioList.get(posicao+2));
            cpf.setText(fincioraioList.get(posicao+5));
            cnh.setText(fincioraioList.get(posicao+6));
            dataAdmissao.setText(fincioraioList.get(posicao+7));
            descricao.setText(fincioraioList.get(posicao+8));

        }

        d.show();
    }

    public void eventoListFuncionario(String i){
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.child("usuario").child(i).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                fincioraioList.clear();
                String temp;
                String temp2;
                String temp3;
                for (DataSnapshot objDataSnapshot:dataSnapshot.getChildren()){
                    f = objDataSnapshot.getValue(Funcionario.class);

                    fincioraioList.add(f.getNome());
                    fincioraioList.add(f.getEnderco());
                    temp = ""+f.getTelefone();
                    fincioraioList.add(temp);
                    fincioraioList.add(f.getEmail());
                    fincioraioList.add(f.getSenha());
                    temp2 = "" + f.getCpf();
                    fincioraioList.add(temp2);
                    temp3 = "" + f.getNmrRegistroCNH();
                    fincioraioList.add(temp3);
                    fincioraioList.add(f.getDataAdmissao());
                    fincioraioList.add(f.getDescricaoHabExp());

                }
                ArrayAdapter<String> funcionarioArrayAdapter = new ArrayAdapter<String>(DlgFunc.this, android.R.layout.simple_list_item_1, fincioraioList);
                listView.setAdapter(funcionarioArrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
            Intent intent = new Intent(DlgFunc.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

            //Intent intent = new Intent(CadastroMaquinarioActivity.this, LoginActivity.class);

        }
        return true;
    }
}
