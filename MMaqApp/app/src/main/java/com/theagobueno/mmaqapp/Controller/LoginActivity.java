package com.theagobueno.mmaqapp.Controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.theagobueno.mmaqapp.ActvBarraProgresso;
import com.theagobueno.mmaqapp.DAO.ConfigFirebase;
import com.theagobueno.mmaqapp.Entidades.Administrador;
import com.theagobueno.mmaqapp.Helper.EstadoApp;
import com.theagobueno.mmaqapp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnEntrar;
    private FirebaseAuth autenticacao;
    private Administrador administrador;


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            if (!EstadoApp.getSingleInstance().isLoggingOut()) {
                finish();
            } else {
                EstadoApp.getSingleInstance().setLoggingOut(false);
                super.onActivityResult(requestCode, resultCode, data);
            }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);



        btnEntrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
                TextView texto = (TextView) findViewById(R.id.texto);

                ActvBarraProgresso barra = new ActvBarraProgresso(LoginActivity.class, progress, texto);
                barra.execute();
                if (!edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals("")){

                    administrador = new Administrador();
                    administrador.setEmail(edtEmail.getText().toString());
                    administrador.setSenha(edtSenha.getText().toString());

                    validaLogin();
                }else{
                    edtSenha.setText("");
                    edtEmail.requestFocus();
                    AlertDialog.Builder dlg = new AlertDialog.Builder(LoginActivity.this);
                    dlg.setTitle("Aviso");
                    dlg.setMessage("Preencha todos os campos!");
                    dlg.setNeutralButton("OK", null);
                    dlg.show();
                }
            }
        });

    }

    public void validaLogin(){

        autenticacao = ConfigFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(administrador.getEmail(), administrador.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    edtSenha.setText("");
                    edtEmail.setText("");
                    edtEmail.requestFocus();
                    Toast.makeText(LoginActivity.this, "Login Efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                    abrirCadastroMaquinario();
                }else{
                    AlertDialog.Builder dlg = new AlertDialog.Builder(LoginActivity.this);
                    dlg.setTitle("Aviso");
                    dlg.setMessage("Usuário ou senha inválido!");
                    dlg.setNeutralButton("OK", null);
                    dlg.show();
                }
            }
        });

    }

    public void abrirCadastroMaquinario(){
        Intent intentAbrirTelaPrincipal = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intentAbrirTelaPrincipal);

    }
}
