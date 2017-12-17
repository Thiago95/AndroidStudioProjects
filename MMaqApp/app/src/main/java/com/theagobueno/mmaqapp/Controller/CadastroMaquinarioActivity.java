package com.theagobueno.mmaqapp.Controller;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.theagobueno.mmaqapp.DAO.ConfigFirebase;
import com.theagobueno.mmaqapp.Entidades.Maquinario;
import com.theagobueno.mmaqapp.R;

import java.util.UUID;

public class CadastroMaquinarioActivity extends AppCompatActivity {

    private EditText edtCadMaqMarca;
    private EditText edtCadMaqModelo;
    private EditText edtCadMaqTipo;
    private EditText edtCadMaqPotencia;
    private EditText edtCadMaqValorAquisicao;
    private EditText edtCadMaqDataAquisicao;

    private Maquinario maquinario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_maquinario);

        edtCadMaqMarca          = (EditText)findViewById(R.id.edtCadMaqMarca);
        edtCadMaqModelo         = (EditText)findViewById(R.id.edtCadMaqModelo);
        edtCadMaqTipo           = (EditText)findViewById(R.id.edtCadMaqTipo);
        edtCadMaqPotencia       = (EditText)findViewById(R.id.edtCadMaqPotencia);
        edtCadMaqValorAquisicao = (EditText)findViewById(R.id.edtCadMaqValorAquisicao);
        edtCadMaqDataAquisicao  = (EditText)findViewById(R.id.edtCadMaqDataAquisicao);;
    }

    public void limparCampos() {
        edtCadMaqMarca.setText("");
        edtCadMaqMarca.requestFocus();
        edtCadMaqModelo.setText("");
        edtCadMaqTipo.setText("");
        edtCadMaqPotencia.setText("");
        edtCadMaqValorAquisicao.setText("");
        edtCadMaqDataAquisicao.setText("");
    }

    public void cadastrarMaquina(){
        try{
            if(!edtCadMaqMarca.getText().toString().equals("") && !edtCadMaqModelo.getText().toString().equals("")){
                if (!edtCadMaqTipo.getText().toString().equals("") && !edtCadMaqPotencia.getText().toString().equals("")) {
                    if (!edtCadMaqValorAquisicao.getText().toString().equals("") && !edtCadMaqDataAquisicao.getText().toString().equals("")) {

                        maquinario = new Maquinario();
                        maquinario.setId(UUID.randomUUID().toString());
                        maquinario.setMarca(edtCadMaqMarca.getText().toString());
                        maquinario.setModelo(edtCadMaqModelo.getText().toString());
                        maquinario.setTipoMaquina(edtCadMaqTipo.getText().toString());
                        maquinario.setPotencia(Integer.parseInt(edtCadMaqPotencia.getText().toString()));
                        maquinario.setValorAquisicao(Integer.parseInt(edtCadMaqValorAquisicao.getText().toString()));
                        maquinario.setDataAquisicao(edtCadMaqDataAquisicao.getText().toString());

                        maquinario.salvar();

                        Toast.makeText(CadastroMaquinarioActivity.this, "Maquinário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                        limparCampos();

                    }else{
                        Toast.makeText(CadastroMaquinarioActivity.this, "Verfique se os campos, Valor da Compra e/ou Data da Compra, estão preenchidos corretamente!", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(CadastroMaquinarioActivity.this, "Verfique se os campos, Tipo de Máquina e/ou Potência, estão preenchidos corretamente!", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(CadastroMaquinarioActivity.this, "Verfique se os campos, Marca e/ou Modelo, estão preenchidos corretamente!", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Toast.makeText(CadastroMaquinarioActivity.this, "Verfique se os campos se os campos foram preenchidos corretamente!", Toast.LENGTH_LONG).show();
        }

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
            cadastrarMaquina();
            abrirMain();
        }else if(id == R.id.menuEdit){
            finish();
        }
        return true;
    }

    public void abrirMain() {
        Intent intent = new Intent(CadastroMaquinarioActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
