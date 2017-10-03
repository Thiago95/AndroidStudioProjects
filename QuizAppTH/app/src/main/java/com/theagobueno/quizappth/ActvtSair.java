package com.theagobueno.quizappth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActvtSair extends AppCompatActivity {

    private Button btnSair;
    private Button btnRefazer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvt_sair);
        btnSair = (Button) findViewById(R.id.btnSair);
        btnRefazer = (Button) findViewById(R.id.btnRefazer);

        btnRefazer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActvtSair.this, ActvPrincipal.class);
                startActivity(intent);
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActvtSair.this, "Fechando QUIZ!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

}
