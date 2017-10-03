package com.theagobueno.prj_firebase.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.theagobueno.prj_firebase.DAO.ConfigFirebase;
import com.theagobueno.prj_firebase.Entidades.Usuario;
import com.theagobueno.prj_firebase.Helper.Base64Custom;
import com.theagobueno.prj_firebase.Helper.Preferencias;
import com.theagobueno.prj_firebase.R;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtCadNome;
    private EditText edtCadSobrenome;
    private EditText edtCadAniversario;
    private EditText edtCadEmail;
    private EditText edtCadSenha;
    private EditText edtCadConfirmarSenha;
    private RadioButton rbMasculino;
    private RadioButton rbFeminino;
    private Button btnCadastrar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //TODO recuperando os dados contidos no xml
        edtCadNome = (EditText) findViewById(R.id.edtCadNome);
        edtCadSobrenome = (EditText) findViewById(R.id.edtCadSobrenome);
        edtCadAniversario = (EditText) findViewById(R.id.edtCadAniversario);
        edtCadEmail = (EditText) findViewById(R.id.edtCadEmail);
        edtCadSenha = (EditText) findViewById(R.id.edtCadSenha);
        edtCadConfirmarSenha = (EditText) findViewById(R.id.edtCadConfirmarSenha);
        rbMasculino = (RadioButton) findViewById(R.id.rbMasculino);
        rbFeminino = (RadioButton) findViewById(R.id.rbFeminino);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtCadSenha.getText().toString().equals(edtCadConfirmarSenha.getText().toString())){

                    usuario = new Usuario();
                    usuario.setNome(edtCadNome.getText().toString());
                    usuario.setSobrenome(edtCadSobrenome.getText().toString());
                    usuario.setAniversario(edtCadAniversario.getText().toString());
                    usuario.setEmail(edtCadEmail.getText().toString());
                    usuario.setSenha(edtCadSenha.getText().toString());

                    if (rbFeminino.isChecked()){
                        usuario.setSexo("Feminino");
                    }else{
                        usuario.setSexo("Masculino");
                    }

                    cadastrarUsuario();

                }else{
                    Toast.makeText(CadastroActivity.this, "As senhas não são correspondetes!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void cadastrarUsuario(){

        autenticacao = ConfigFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();

                    String identificadorUsuario = Base64Custom.codificadorBase64(usuario.getEmail());
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuario.setId(identificadorUsuario);
                    usuario.salar();

                    Preferencias preferencias = new Preferencias(CadastroActivity.this);
                    preferencias.salvarUsuarioPreferencias(identificadorUsuario, usuario.getNome());

                    abrirLoginUsuario();

                }else{

                    String erroExecao = "";

                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        erroExecao = " Por favor informe uma senha mais forte, com no mínimo 8 caracteres, com letras e números.";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        erroExecao = " Por favor informe um e-mail válido.";
                    } catch (FirebaseAuthUserCollisionException e){
                        erroExecao = " E-mail já cadastrado no sistema!";
                    } catch (Exception e) {
                        erroExecao = " Erro ao efetuar cadastro!";
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this, "Erro: " + erroExecao, Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    public void abrirLoginUsuario(){
        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
