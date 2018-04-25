package com.theagobueno.mmaqapp.Controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.theagobueno.mmaqapp.Entidades.Funcionario;
import com.theagobueno.mmaqapp.Entidades.Manutencao;
import com.theagobueno.mmaqapp.Entidades.Maquinario;
import com.theagobueno.mmaqapp.Helper.EstadoApp;
import com.theagobueno.mmaqapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class CadastroManutencaoActivity extends AppCompatActivity {

    private TextView txtViewCadCodMaquinario;
    private EditText edtCadTipoManut;
    private EditText edtCadDataAtualManutencao;
    private EditText edtCadDataProximaManutencao;
    private EditText edtCadValorManutencao;
    private EditText edtCadDescricaoManutencao;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private DatePickerFragment maskDate;
    Manutencao manutencao = new Manutencao();
    Maquinario maquinario = new Maquinario();
    Funcionario funcionario = new Funcionario();
    String id;
    String marca;
    String modelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_manutencao);
        Locale mLocale = new Locale("pt", "BR");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        marca = bundle.getString("marca");
        txtViewCadCodMaquinario = (TextView)findViewById(R.id.txtViewCadCodMaquinario);
        txtViewCadCodMaquinario.setText(marca);
        edtCadTipoManut = (EditText)findViewById(R.id.edtCadTipoManut);
        edtCadDataAtualManutencao = (EditText)findViewById(R.id.edtCadDataAtualManutencao);
        edtCadDataAtualManutencao.addTextChangedListener(maskDate.insert("##/##/####", edtCadDataAtualManutencao));
        edtCadDataProximaManutencao = (EditText)findViewById(R.id.edtCadDataProximaManutencao);
        edtCadDataProximaManutencao.addTextChangedListener(maskDate.insert("##/##/####", edtCadDataProximaManutencao));
        edtCadValorManutencao = (EditText)findViewById(R.id.edtCadValorManutencao);
        edtCadValorManutencao.addTextChangedListener(new DatePickerFragment(edtCadValorManutencao, mLocale));
        edtCadDescricaoManutencao = (EditText)findViewById(R.id.edtCadDescricaoManutencao);



        inicializaFirebase();
    }

    public void validaCampos() throws ParseException {
        try{
            boolean res = false;

            String tipo = edtCadTipoManut.getText().toString();
            String valor = edtCadValorManutencao.getText().toString();
            String data = edtCadDataAtualManutencao.getText().toString();
            String data2 = edtCadDataProximaManutencao.getText().toString();


            if (res = isValidaCampoString(tipo)) {
                edtCadTipoManut.requestFocus();
            }
            else if (res = isValidaCampoString(valor)) {
                edtCadValorManutencao.requestFocus();
            }
            else if (res = !isValidaDataAntes(data)) {
                edtCadDataAtualManutencao.requestFocus();
            }
            else if (res = !isValidaDataDepois(data2)) {
                edtCadDataProximaManutencao.requestFocus();
            }

            if (res){
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle("Aviso");
                dlg.setMessage("Verfique se os campos estão preenchidos corretamente!");
                dlg.setNeutralButton("OK", null);
                dlg.show();
            }else{
                cadastrarManutencao();
            }
        }catch (Exception e){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage("Retire os pontos e/ou virgulas do valor da compra!");
            dlg.setNeutralButton("OK", null);
            dlg.show();
            edtCadValorManutencao.requestFocus();
        }

    }

    private boolean isValidaCampoString(String valor){
        boolean result = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return result;
    }

    private boolean isValidaDataDepois(String data2) throws ParseException {
        boolean var = false;
        if(data2 == null){
            var = true;
        }else{
            Date dtAtual = new Date();
            Date dtInserida = new SimpleDateFormat("dd/MM/yyyy").parse(data2);
            Integer anoA = dtAtual.getYear();
            Integer mesA = dtAtual.getMonth();
            Integer diaA = dtAtual.getDay();
            Integer anoB = dtInserida.getYear();
            Integer mesB = dtInserida.getMonth();
            Integer diaB = dtInserida.getDay();
            if (anoB > anoA) {
                if (mesB > mesA){
                    if (diaB > diaA) {
                        var = true;
                    }
                }
            }
        }
        return var;
    }

    private boolean isValidaDataAntes(String data) throws ParseException {
        boolean var = false;
        Date dtAtual = new Date();
        Date dtInserida = new SimpleDateFormat("dd/MM/yyyy").parse(data);
        Integer anoA = dtAtual.getYear();
        Integer mesA = dtAtual.getMonth();
        Integer diaA = dtAtual.getDay();
        Integer anoB = dtInserida.getYear();
        Integer mesB = dtInserida.getMonth();
        Integer diaB = dtInserida.getDay();
        if (anoB <= anoA) {
            if (mesB <= mesA){
                if (diaB <= diaA) {
                    var = true;
                }
            }
        }
        return var;
    }

    public void cadastrarManutencao() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getString("id");

        manutencao.setIdManutencao(UUID.randomUUID().toString());
        manutencao.setIdMaquinario(txtViewCadCodMaquinario.getText().toString());
        manutencao.setTipoManutencao(edtCadTipoManut.getText().toString());
        manutencao.setDataAtualManutencao(edtCadDataAtualManutencao.getText().toString());
        manutencao.setDataProximaMatencao(edtCadDataProximaManutencao.getText().toString());
        manutencao.setCustoManutencao(edtCadValorManutencao.getText().toString());
        manutencao.setDescriocaoManutencao(edtCadDescricaoManutencao.getText().toString());

        databaseReference.child("maquinario").child(id).child("manutencao").child(manutencao.getIdManutencao()).setValue(manutencao);
        databaseReference.child("manutecao").child(manutencao.getIdManutencao()).setValue(manutencao);

            Toast.makeText(CadastroManutencaoActivity.this, "Manutenção cadastrado com sucesso!", Toast.LENGTH_LONG).show();
        abrirMain();

    }

    public void limparCampos() {
        edtCadDataAtualManutencao.setText("");
        edtCadDataProximaManutencao.setText("");
        edtCadValorManutencao.setText("");
        edtCadDescricaoManutencao.setText("");
    }

    public void inicializaFirebase() {
        FirebaseApp.initializeApp(CadastroManutencaoActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
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
            Intent intent = new Intent(CadastroManutencaoActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

            //Intent intent = new Intent(CadastroMaquinarioActivity.this, LoginActivity.class);

        }
        return true;
    }

    public void abrirMain() {
        Intent intent = new Intent(CadastroManutencaoActivity.this, MainActivity.class);
        startActivity(intent);
    }


}
