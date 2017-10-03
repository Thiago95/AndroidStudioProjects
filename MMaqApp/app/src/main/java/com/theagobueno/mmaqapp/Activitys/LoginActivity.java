package com.theagobueno.mmaqapp.Activitys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.theagobueno.mmaqapp.DAO.ConfigFirebase;
import com.theagobueno.mmaqapp.Entidades.Administrador;
import com.theagobueno.mmaqapp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnEntrar;
    private FirebaseAuth autenticacao;
    private Administrador administrador;

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
                if (!edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals("")){

                    administrador = new Administrador();
                    administrador.setEmail(edtEmail.getText().toString());
                    administrador.setSenha(edtSenha.getText().toString());

                    validaLogin();
                    edtSenha.setText("");
                    edtEmail.setText("");
                    edtEmail.requestFocus();

                }else{
                    edtSenha.setText("");
                    edtEmail.requestFocus();
                    Toast.makeText(LoginActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void validaLogin(){

        autenticacao = ConfigFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(administrador.getEmail(), administrador.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    abrirCadastroMaquinario();
                    Toast.makeText(LoginActivity.this, "Login Efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Usuário ou senha inválido!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void abrirCadastroMaquinario(){
        Intent intentAbrirTelaPrincipal = new Intent(LoginActivity.this, CadastroMaquinarioActivity.class);
        startActivity(intentAbrirTelaPrincipal);
    }
}
