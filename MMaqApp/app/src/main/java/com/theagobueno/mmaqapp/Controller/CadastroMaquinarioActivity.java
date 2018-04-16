package com.theagobueno.mmaqapp.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.theagobueno.mmaqapp.Helper.EstadoApp;
import com.theagobueno.mmaqapp.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class CadastroMaquinarioActivity extends AppCompatActivity {

    public EditText edtCadMaqMarca;
    public EditText edtCadMaqModelo;
    public EditText edtCadMaqTipo;
    public EditText edtCadMaqPotencia;
    public EditText edtCadMaqValorAquisicao;
    public EditText edtCadMaqDataAquisicao;

    private Maquinario maquinario;
    private DatePickerFragment maskDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_maquinario);
        Locale mLocale = new Locale("pt", "BR");
        edtCadMaqMarca          = (EditText)findViewById(R.id.edtCadMaqMarca);
        edtCadMaqModelo         = (EditText)findViewById(R.id.edtCadMaqModelo);
        edtCadMaqTipo           = (EditText)findViewById(R.id.edtCadMaqTipo);
        edtCadMaqPotencia       = (EditText)findViewById(R.id.edtCadMaqPotencia);
        edtCadMaqValorAquisicao = (EditText)findViewById(R.id.edtCadMaqValorAquisicao);

        edtCadMaqValorAquisicao.addTextChangedListener(new DatePickerFragment(edtCadMaqValorAquisicao, mLocale));
        edtCadMaqDataAquisicao  = (EditText)findViewById(R.id.edtCadMaqDataAquisicao);
        edtCadMaqDataAquisicao.addTextChangedListener(maskDate.insert("##/##/####", edtCadMaqDataAquisicao));
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

    public void validaCampos() throws ParseException {

        try{
            boolean res = false;
            String marca = edtCadMaqMarca.getText().toString();
            String modelo = edtCadMaqModelo.getText().toString();
            String tipo = edtCadMaqTipo.getText().toString();
            String potencia = edtCadMaqPotencia.getText().toString();
            String valor = edtCadMaqValorAquisicao.getText().toString();
            String data = edtCadMaqDataAquisicao.getText().toString();
            if(res = isValidaCampoString(marca)) {
                edtCadMaqMarca.requestFocus();
            }else if (res = isValidaCampoString(modelo)) {
                edtCadMaqModelo.requestFocus();
            }else if (res = isValidaCampoString(tipo)) {
                edtCadMaqTipo.requestFocus();
            }else if (res = isValidaCampoString(potencia)) {
                edtCadMaqPotencia.requestFocus();
            }else if (res = isValidaCampoString(valor)) {
                edtCadMaqValorAquisicao.requestFocus();
            }else if (res = !isValidaData(data)) {
                edtCadMaqDataAquisicao.requestFocus();
            }
            if (res){
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle("Aviso");
                dlg.setMessage("Verfique se os campos estão preenchidos corretamente!");
                dlg.setNeutralButton("OK", null);
                dlg.show();
            }else{
                cadMaquina();
            }
        }catch (Exception e){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage("Retire os pontos e/ou virgulas do valor da compra!");
            dlg.setNeutralButton("OK", null);
            dlg.show();
            edtCadMaqValorAquisicao.requestFocus();
        }


    }

    private boolean isValidaCampoString(String valor){
        boolean result = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return result;
    }



    private boolean isValidaData(String data) throws ParseException {
        boolean var = false;
        Date dtAtual = new Date();
        Date dtInserida = new SimpleDateFormat("dd/mm/yyyy").parse(data);
        if (dtInserida.before(dtAtual)) {
            var = true;
        }

        return var;
    }

    public void cadMaquina(){
        maquinario = new Maquinario();
        maquinario.setId(UUID.randomUUID().toString());
        maquinario.setMarca(edtCadMaqMarca.getText().toString());
        maquinario.setModelo(edtCadMaqModelo.getText().toString());
        maquinario.setTipoMaquina(edtCadMaqTipo.getText().toString());
        maquinario.setPotencia(Integer.parseInt(edtCadMaqPotencia.getText().toString()));
        maquinario.setValorAquisicao(edtCadMaqValorAquisicao.getText().toString());
        maquinario.setDataAquisicao(edtCadMaqDataAquisicao.getText().toString());

        maquinario.salvar();
        abrirMain();
        Toast.makeText(CadastroMaquinarioActivity.this, "Maquinário cadastrado com sucesso!", Toast.LENGTH_LONG).show();
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
            try {
                validaCampos();

            } catch (ParseException e) {
                e.printStackTrace();
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle("Aviso");
                dlg.setMessage("Verfique se os campos estão preenchidos corretamente!");
                dlg.setNeutralButton("OK", null);
                dlg.show();
            }

        }else if(id == R.id.menuSair){
            SharedPreferences myPrefs = getSharedPreferences("MY", MODE_PRIVATE);
            SharedPreferences.Editor editor = myPrefs.edit();
            editor.clear();
            editor.commit();
            EstadoApp.getSingleInstance().setLoggingOut(true);
            Intent intent = new Intent(CadastroMaquinarioActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        return true;
    }

    public void abrirMain() {
        Intent intent = new Intent(CadastroMaquinarioActivity.this, MainActivity.class);
        startActivity(intent);
    }


}
