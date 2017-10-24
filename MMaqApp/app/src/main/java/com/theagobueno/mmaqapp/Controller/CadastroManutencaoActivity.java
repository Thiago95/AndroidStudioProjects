package com.theagobueno.mmaqapp.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.theagobueno.mmaqapp.Entidades.Manutencao;
import com.theagobueno.mmaqapp.R;

import java.util.UUID;

public class CadastroManutencaoActivity extends AppCompatActivity {
    private Spinner spinner;
    private TextView txtViewCadCodMaquinario;
    private TextView txtViewCadCodFuncionario;
    private EditText edtCadDataAtualManutencao;
    private EditText edtCadDataProximaManutencao;
    private EditText edtCadValorManutencao;
    private EditText edtCadDescricaoManutencao;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    Manutencao manutencao = new Manutencao();
    private String[] itensSpinner = new String[]{"tipo1","tipo2","tipo3","tipo4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_manutencao);
        txtViewCadCodMaquinario = (TextView)findViewById(R.id.txtViewCadCodMaquinario);
        txtViewCadCodMaquinario.setText("testeMaq1");
        txtViewCadCodFuncionario = (TextView)findViewById(R.id.txtViewCadCodFuncionario);
        txtViewCadCodFuncionario.setText("testeFunc1");
        edtCadDataAtualManutencao = (EditText)findViewById(R.id.edtCadDataAtualManutencao);
        edtCadDataProximaManutencao = (EditText)findViewById(R.id.edtCadDataProximaManutencao);
        edtCadValorManutencao = (EditText)findViewById(R.id.edtCadValorManutencao);
        edtCadDescricaoManutencao = (EditText)findViewById(R.id.edtCadDescricaoManutencao);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,itensSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        inicializaFirebase();
    }

    private void cadastrarManutencao() {
        try{
            if(!txtViewCadCodMaquinario.getText().toString().equals("") && !txtViewCadCodFuncionario.getText().toString().equals("")){
                if (!edtCadDataAtualManutencao.getText().toString().equals("") && !edtCadDataProximaManutencao.getText().toString().equals("")) {
                    if (!edtCadValorManutencao.getText().toString().equals("") && !edtCadDescricaoManutencao.getText().toString().equals("")) {

                        manutencao.setIdManutencao(UUID.randomUUID().toString());
                        manutencao.setIdMaquinario(txtViewCadCodMaquinario.getText().toString());
                        manutencao.setIdFuncionario(txtViewCadCodFuncionario.getText().toString());
                        manutencao.setTipoManutencao(spinner.getSelectedItem().toString());
                        manutencao.setDataAtualManutencao(edtCadDataAtualManutencao.getText().toString());
                        manutencao.setDataProximaMatencao(edtCadDataProximaManutencao.getText().toString());
                        manutencao.setCustoManutencao(Integer.parseInt(edtCadValorManutencao.getText().toString()));
                        manutencao.setDescriocaoManutencao(edtCadDescricaoManutencao.getText().toString());

                        databaseReference.child("manutencao").child(manutencao.getIdManutencao()).setValue(manutencao);

                        Toast.makeText(CadastroManutencaoActivity.this, "Maquinário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                        limparCampos();

                    }else{
                        Toast.makeText(CadastroManutencaoActivity.this, "Verfique se os campos, Valor da Compra e/ou Data da Compra, estão preenchidos corretamente!", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(CadastroManutencaoActivity.this, "Verfique se os campos, Tipo de Máquina e/ou Potência, estão preenchidos corretamente!", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(CadastroManutencaoActivity.this, "Verfique se os campos, Marca e/ou Modelo, estão preenchidos corretamente!", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Toast.makeText(CadastroManutencaoActivity.this, "Verfique se os campos se os campos foram preenchidos corretamente!", Toast.LENGTH_LONG).show();
        }
    }

    private void limparCampos() {
        edtCadDataAtualManutencao.setText("");
        edtCadDataProximaManutencao.setText("");
        edtCadValorManutencao.setText("");
        edtCadDescricaoManutencao.setText("");
    }

    private void inicializaFirebase() {
        FirebaseApp.initializeApp(CadastroManutencaoActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void mantrarElemento(View view){
        String nome = (String) spinner.getSelectedItem();
        long id = spinner.getSelectedItemId();
        int position = spinner.getSelectedItemPosition();

        Toast.makeText(this, "Carro: "+nome+" Id: "+id+" Posição: "+position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuAdicionar){
            cadastrarManutencao();
            abrirMain();
        }
        return true;
    }

    private void abrirMain() {
        Intent intent = new Intent(CadastroManutencaoActivity.this, MainActivity.class);
        startActivity(intent);
    }


}
