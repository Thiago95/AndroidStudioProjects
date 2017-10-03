package com.theagobueno.quizappth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActvPrincipal extends AppCompatActivity {

    private TextView txtVwPontos;
    private TextView txtVwPergunta;
    private Button btnChoiceUm;
    private Button btnChoiceDois;
    private Button btnChoiceTres;
    private Button btnChoiceQuatro;
    private Button btnExit;
    private String answer;
    private int pontuacao = 0;
    private int nmrPergunta = 0;

    BbltcPerguntaResposta perguntaResposta = new BbltcPerguntaResposta();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actv_principal);

        txtVwPontos = (TextView)findViewById(R.id.txtVwPontos);
        txtVwPergunta = (TextView)findViewById(R.id.txtVwPergunta);
        btnChoiceUm = (Button)findViewById(R.id.btnChoiceUm);
        btnChoiceDois = (Button)findViewById(R.id.btnChoiceDois);
        btnChoiceTres = (Button)findViewById(R.id.btnChoiceTres);
        btnChoiceQuatro = (Button)findViewById(R.id.btnChoiceQuatro);
        btnExit = (Button)findViewById(R.id.btnExit);
        updadtePergunta();

        btnChoiceUm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnChoiceUm.getText() == answer){
                    pontuacao = pontuacao + 1;
                    updatePontuacao(pontuacao);
                    updadtePergunta();
                    Toast.makeText(ActvPrincipal.this, "Acertô Mizeravi!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ActvPrincipal.this, "Eroou!", Toast.LENGTH_LONG).show();
                    updadtePergunta();
                }
            }
        });

        btnChoiceDois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnChoiceDois.getText() == answer){
                    pontuacao = pontuacao + 1;
                    updatePontuacao(pontuacao);
                    updadtePergunta();
                    Toast.makeText(ActvPrincipal.this, "Acertô Mizeravi!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ActvPrincipal.this, "Eroou!", Toast.LENGTH_LONG).show();
                    updadtePergunta();
                }
            }
        });

        btnChoiceTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnChoiceTres.getText() == answer){
                    pontuacao = pontuacao + 1;
                    updatePontuacao(pontuacao);
                    updadtePergunta();
                    Toast.makeText(ActvPrincipal.this, "Acertô Mizeravi!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ActvPrincipal.this, "Eroou!", Toast.LENGTH_LONG).show();
                    updadtePergunta();
                }
            }
        });

        btnChoiceQuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnChoiceQuatro.getText() == answer){
                    pontuacao = pontuacao + 1;
                    updatePontuacao(pontuacao);
                    updadtePergunta();
                    Toast.makeText(ActvPrincipal.this, "Acertô Mizeravi!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ActvPrincipal.this, "Eroou!", Toast.LENGTH_LONG).show();
                    updadtePergunta();
                }
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTelaFinal();
                finish();
            }
        });
    }

    private void updadtePergunta(){

        if(nmrPergunta >= 10){
            Toast.makeText(ActvPrincipal.this, "Você fez " + pontuacao + " pontos!", Toast.LENGTH_LONG).show();
            abrirTelaFinal();
            finish();
        }else{
            txtVwPergunta.setText(perguntaResposta.getPergunta(nmrPergunta));
            btnChoiceUm.setText(perguntaResposta.getResposta1(nmrPergunta));
            btnChoiceDois.setText(perguntaResposta.getResposta2(nmrPergunta));
            btnChoiceTres.setText(perguntaResposta.getResposta3(nmrPergunta));
            btnChoiceQuatro.setText(perguntaResposta.getResposta4(nmrPergunta));
            answer = perguntaResposta.getRespostaCerta(nmrPergunta);
            nmrPergunta++;
        }
    }

    public void updatePontuacao(int point){
        txtVwPontos.setText("" + pontuacao);
    }
    public String upPonto(){
        String ponto= ""+pontuacao;
        return ponto;
    }

    public void abrirTelaFinal(){
        Intent intent = new Intent(ActvPrincipal.this, ActvtSair.class);
        startActivity(intent);


    }

}
