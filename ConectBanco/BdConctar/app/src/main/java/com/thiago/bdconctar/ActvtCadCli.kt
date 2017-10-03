package com.thiago.bdconctar

import android.graphics.MaskFilter
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.TextUtils
import android.util.Patterns
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast

class ActvtCadCli : AppCompatActivity() {

    private var edtTxtNome: EditText? = null
    private var edtTxtEndereco: EditText? = null
    private var edtTxtEmail: EditText? = null
    private var edtTxtTelefone: EditText? = null

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvt_cad_cli)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        edtTxtNome = findViewById(R.id.edtTxtNome) as EditText
        edtTxtEndereco = findViewById(R.id.edtTxtEndereco) as EditText
        edtTxtEmail = findViewById(R.id.edtTxtEmail) as EditText
        edtTxtTelefone = findViewById(R.id.edtTxtTelefone) as EditText
        edtTxtTelefone!!.addTextChangedListener(PhoneNumberFormattingTextWatcher("BR"))

    }

    //TODO função para validar os campos do cad cli
    private fun validaCampos() {

        var res = false

        //TODO recupera os valores de cada campo
        //TODO getText recupera o valor do campo e o toSring renasforma ele em string
        val nome = edtTxtNome!!.text.toString()
        val endereco = edtTxtEndereco!!.text.toString()
        val email = edtTxtEmail!!.text.toString()
        val telefone = edtTxtTelefone!!.text.toString()

        if (isCampoVazio(nome)) {
            edtTxtNome!!.requestFocus()
            res = true
        } else if (isCampoVazio(endereco)) {
            edtTxtEndereco!!.requestFocus()
            res = true
        } else if (res = !isValidaEmail(email)) {
            edtTxtEmail!!.requestFocus()
            //res = true;
        } else if (isCampoVazio(telefone)) {
            edtTxtTelefone!!.requestFocus()
            res = true
        }

        //TODO sif responsavel por mostrar um alert dialog com advertecia
        if (res) {

            val dlg = AlertDialog.Builder(this)
            dlg.setTitle(R.string.title_aviso)
            dlg.setMessage(R.string.message_campo_invalido_branco)
            dlg.setNeutralButton(R.string.lbl_ok, null)
            dlg.show()


        }

    }

    //TODO função que verifica se o campo está vazio ou não
    //TODO a utilização do isXXX é importate ser usado sempre que uma função for retornar valores booleanos
    private fun isCampoVazio(vlr: String): Boolean {
        //TODO isEmpty para verificar se há caracteres no campo
        //TODO e o trim para retirar os espaços em branco já que eles são contados como caracter na isEmpty
        return TextUtils.isEmpty(vlr) || vlr.trim { it <= ' ' }.isEmpty()


    }

    //TODO função para validação do campo de email corretamente
    private fun isValidaEmail(email: String): Boolean {
        //TODO testa se o campo email NÂO está vazio e se o email é valido
        return !isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //TODO responsalvel por mostrar o menu ok/cancelar ma tela cliente
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_actvt_cad_cli, menu)

        return super.onCreateOptionsMenu(menu)
    }

    //TODO responsavel por tratar os comandos do menu ok/cancelar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        when (id) {

            R.id.action_ok ->
                //TODO exive uma mensagem de confirmação da ação
                //Toast.makeText(this, "Botão OK selecionado", Toast.LENGTH_SHORT).show();
                validaCampos()
            R.id.action_cancel -> {
                //TODO exive uma mensagem de confirmação da ação
                Toast.makeText(this, "Botão Cancelar selecionado", Toast.LENGTH_SHORT).show()
                //TODO ação que finaliza a ação da actvt cad cli
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
