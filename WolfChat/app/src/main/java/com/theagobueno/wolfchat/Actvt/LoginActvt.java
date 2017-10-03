package com.theagobueno.wolfchat.Actvt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.theagobueno.wolfchat.R;

public class LoginActvt extends AppCompatActivity {

    private EditText edtUsuario;
    private EditText edtSenha;
    private Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_actvt);

        edtUsuario = (EditText)findViewById(R.id.edtUsuario);
        edtSenha = (EditText)findViewById(R.id.edtSenha);
        btnEntrar = (Button)findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMainActvt();
            }
        });

    }

    private void abrirMainActvt() {
        Intent intent = new Intent(LoginActvt.this, MainActvt.class);
        startActivity(intent);
    }

}
