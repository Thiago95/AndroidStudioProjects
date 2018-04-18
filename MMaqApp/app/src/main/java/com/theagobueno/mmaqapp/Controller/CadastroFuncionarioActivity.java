package com.theagobueno.mmaqapp.Controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;

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
    private DatePickerFragment maskDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_funcionario);
        Locale mLocale = new Locale("pt", "BR");

        edtCadFuncNome = (EditText)findViewById(R.id.edtCadFuncNome);
        edtCadFuncEndereco = (EditText)findViewById(R.id.edtCadFuncEndereco);
        edtCadFuncTelefone = (EditText)findViewById(R.id.edtCadFuncTelefone);
        edtCadFuncEmail = (EditText)findViewById(R.id.edtCadFuncEmail);
        edtCadFuncSenha = (EditText)findViewById(R.id.edtCadFuncSenha);
        edtCadFuncConfirmaSenha = (EditText)findViewById(R.id.edtCadFuncConfirmaSenha);
        edtCadFuncCPF = (EditText)findViewById(R.id.edtCadFuncCPF);
        edtCadFuncCPF.addTextChangedListener(maskDate.insertCpf(edtCadFuncCPF));
        edtCadFuncCNH = (EditText)findViewById(R.id.edtCadFuncCNH);
        edtCadFuncDataAdmissao = (EditText)findViewById(R.id.edtCadFuncDataAdmissao);
        edtCadFuncDataAdmissao.addTextChangedListener(maskDate.insert("##/##/####", edtCadFuncDataAdmissao));
        edtCadFuncDescricao = (EditText)findViewById(R.id.edtCadFuncDescricao);

    }

    public void validaCampos(){
        try{
            boolean res = false;
            String nome = edtCadFuncNome.getText().toString();
            String endereco = edtCadFuncEndereco.getText().toString();
            String telefone = edtCadFuncTelefone.getText().toString();
            String email = edtCadFuncEmail.getText().toString();
            String senha = edtCadFuncSenha.getText().toString();
            String cpf = edtCadFuncCPF.getText().toString();
            String cnh = edtCadFuncCNH.getText().toString();
            String dataAdmissao = edtCadFuncDataAdmissao.getText().toString();
            if(res = isValidaCampoString(nome)) {
                edtCadFuncNome.requestFocus();
            }else if (res = isValidaCampoString(endereco)) {
                edtCadFuncEndereco.requestFocus();
            }else if (res = isValidaCampoString(telefone)) {
                edtCadFuncTelefone.requestFocus();
            }else if (res = isValidaCampoString(email)) {
                edtCadFuncEmail.requestFocus();
            }else if (res = isValidaCampoString(senha)) {
                edtCadFuncSenha.requestFocus();
            }else if (res = !isValidaCampoCpf(cpf)) {
                edtCadFuncCPF.requestFocus();
            }else if (res = isValidaCampoString(cnh)) {
                edtCadFuncCNH.requestFocus();
            }else if (res = !isValidaData(dataAdmissao)) {
                edtCadFuncDataAdmissao.requestFocus();
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle("Aviso");
                dlg.setMessage("Não é possível cadastrar um funcionário que ainda não está trabalhando!");
                dlg.setNeutralButton("OK", null);
                dlg.show();
            }
            if (res){
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle("Aviso");
                dlg.setMessage("Verfique se os campos estão preenchidos corretamente!");
                dlg.setNeutralButton("OK", null);
                dlg.show();
            }else{
                setDadosFuncionario();
            }
        }catch (Exception e){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage("Retire os pontos e/ou virgulas do valor da compra!");
            dlg.setNeutralButton("OK", null);
            dlg.show();
            edtCadFuncTelefone.requestFocus();
        }

    }

    private boolean isValidaCampoString(String valor){
        boolean result = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return result;
    }

    private boolean isValidaCampoCpf(String CPF){
        if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            }else{
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)){
                dig11 = '0';
            }else {
                dig11 = (char)(r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return (true);
            }else{
                return(false);
            }
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

    private boolean isValidaData(String data) throws ParseException {
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
                    AlertDialog.Builder dlg = new AlertDialog.Builder(CadastroFuncionarioActivity.this);
                    dlg.setTitle("Aviso");
                    dlg.setMessage("Erro: " + erroExecao);
                    dlg.setNeutralButton("OK", null);
                    dlg.show();
                }
            }
        });
    }

    public void setDadosFuncionario(){
        if (edtCadFuncSenha.getText().toString().equals(edtCadFuncConfirmaSenha.getText().toString())) {
            funcionario = new Funcionario();
            funcionario.setNome(edtCadFuncNome.getText().toString());
            funcionario.setEnderco(edtCadFuncEndereco.getText().toString());
            funcionario.setTelefone(edtCadFuncTelefone.getText().toString());
            funcionario.setEmail(edtCadFuncEmail.getText().toString());
            funcionario.setSenha(edtCadFuncSenha.getText().toString());
            funcionario.setCpf(edtCadFuncCPF.getText().toString());
            funcionario.setNmrRegistroCNH(edtCadFuncCNH.getText().toString());
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
