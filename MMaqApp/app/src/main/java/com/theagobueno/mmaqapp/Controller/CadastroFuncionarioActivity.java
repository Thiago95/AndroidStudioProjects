package com.theagobueno.mmaqapp.Controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.theagobueno.mmaqapp.DAO.ConfigFirebase;
import com.theagobueno.mmaqapp.Entidades.Funcionario;
import com.theagobueno.mmaqapp.Helper.Base64Custom;
import com.theagobueno.mmaqapp.Helper.EstadoApp;
import com.theagobueno.mmaqapp.R;

public class CadastroFuncionarioActivity extends AppCompatActivity {

    private EditText edtCadFuncNome;
    private EditText edtCadFuncEndereco;
    private EditText edtCadFuncTelefone;
    private EditText edtCadFuncEmail;
    private EditText edtCadFuncSenha;
    private EditText edtCadFuncConfirmaSenha;
    private EditText edtCadFuncCPF;
    private EditText edtCadFuncCNH;
    private EditText edtCadFuncDataAdmissao;
    private EditText edtCadFuncDescricao;

    private Funcionario funcionario;
    private FirebaseAuth autentica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_funcionario);

        edtCadFuncNome = (EditText)findViewById(R.id.edtCadFuncNome);
        edtCadFuncEndereco = (EditText)findViewById(R.id.edtCadFuncEndereco);
        edtCadFuncTelefone = (EditText)findViewById(R.id.edtCadFuncTelefone);
        edtCadFuncEmail = (EditText)findViewById(R.id.edtCadFuncEmail);
        edtCadFuncSenha = (EditText)findViewById(R.id.edtCadFuncSenha);
        edtCadFuncConfirmaSenha = (EditText)findViewById(R.id.edtCadFuncConfirmaSenha);
        edtCadFuncCPF = (EditText)findViewById(R.id.edtCadFuncCPF);
        edtCadFuncCNH = (EditText)findViewById(R.id.edtCadFuncCNH);
        edtCadFuncDataAdmissao = (EditText)findViewById(R.id.edtCadFuncDataAdmissao);
        edtCadFuncDescricao = (EditText)findViewById(R.id.edtCadFuncDescricao);

    }

    private void limparCampos() {
        edtCadFuncNome.setText("");
        edtCadFuncNome.requestFocus();
        edtCadFuncEndereco.setText("");
        edtCadFuncTelefone.setText("");
        edtCadFuncEmail.setText("");
        edtCadFuncSenha.setText("");
        edtCadFuncConfirmaSenha.setText("");
        edtCadFuncCPF.setText("");
        edtCadFuncCNH.setText("");
        edtCadFuncDataAdmissao.setText("");
        edtCadFuncDescricao.setText("");

    }

    public void cadastrarFuncionario(){

        autentica = ConfigFirebase.getFirebaseAutenticacao();
        autentica.createUserWithEmailAndPassword(
                funcionario.getEmail(),
                funcionario.getSenha()
        ).addOnCompleteListener(CadastroFuncionarioActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){


                    String identificadorUsuario = Base64Custom.codificadorBase64(funcionario.getEmail());
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    funcionario.setId(identificadorUsuario);
                    funcionario.salvar();

                    Toast.makeText(CadastroFuncionarioActivity.this, "Funcionário cadastrado com sucesso!", Toast.LENGTH_LONG).show();

                    abrirMain();

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
                    Toast.makeText(CadastroFuncionarioActivity.this, "Erro: " + erroExecao, Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    public void setDadosFuncionario(){
        if (edtCadFuncSenha.getText().toString().equals(edtCadFuncConfirmaSenha.getText().toString())) {

            funcionario = new Funcionario();
            funcionario.setNome(edtCadFuncNome.getText().toString());
            funcionario.setEnderco(edtCadFuncEndereco.getText().toString());
            funcionario.setTelefone(Integer.parseInt(edtCadFuncTelefone.getText().toString()));
            funcionario.setEmail(edtCadFuncEmail.getText().toString());
            funcionario.setSenha(edtCadFuncSenha.getText().toString());
            funcionario.setCpf(Integer.parseInt(edtCadFuncCPF.getText().toString()));
            funcionario.setNmrRegistroCNH(Integer.parseInt(edtCadFuncCNH.getText().toString()));
            funcionario.setDataAdmissao(edtCadFuncDataAdmissao.getText().toString());
            funcionario.setDescricaoHabExp(edtCadFuncDescricao.getText().toString());

            cadastrarFuncionario();
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
            setDadosFuncionario();;
            finish();
        }else if(id == R.id.menuSair){
            SharedPreferences myPrefs = getSharedPreferences("MY", MODE_PRIVATE);
            SharedPreferences.Editor editor = myPrefs.edit();
            editor.clear();
            editor.commit();
            EstadoApp.getSingleInstance().setLoggingOut(true);
            Intent intent = new Intent(CadastroFuncionarioActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

            //Intent intent = new Intent(CadastroMaquinarioActivity.this, LoginActivity.class);

        }
        return true;
    }

    private void abrirMain() {
        Intent intent = new Intent(CadastroFuncionarioActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }




}
