package com.theagobueno.quizappth;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by thiag on 21/09/2017.
 */

public class BbltcPerguntaResposta {

public void teste (ActvPrincipal m){
    /*Intent secondActivity = new Intent(m, ActvtSair.class);
    Bundle bundle = new Bundle();

    bundle.putString("pontos", String.valueOf(m.upPonto()));
    secondActivity.putExtras(bundle);

    m.startActivity(secondActivity);
    Intent intent = getIntent();

    Bundle bundle = intent.getExtras();

    String txt = bundle.getString("pontos");

    pontos.setText("");
    pontos.setText(txt);*/
}



    private String perguntas [] = {
            "1 - Segundo o interpretado por Marília Mendonça em Amante Não Tem Lar, qual o preço que se paga por ser amante?",
            "2 - De acordo com a interpretação de Simone e Simaria na música Regime Fechado, qual o crime que os envolvidos estão sendo condenados?",
            "3 - Na música Infiel interpretada por Marílie Mendonça, do que ela faz a seu companheiro ao descobrir a traição?",
            "4 - Na interpretação de Henrique e Juliano em Aquela Pessoa, o que está escrito na portaria do coração?",
            "5 - 50 Reais, música interpretada por Niara Azevedo, o que ela faz pra ajudar o casalzinho?",
            "6 - Em Cadeira de Aço, música interpretada pela dupla Zé Neto e Cristiano, o que eles pedem para o garçom fazer com a lata de refrigerante?",
            "7 - O que o rapaz pretende fazer quando chegar na casa de sua ex, na música Amigo Taxista interpretada por Zé Neto e Cristiano?",
            "8 - Qual o número da dose que o cara está bebendo, segundo a interpretação de Matheus e Kauan da música Oitava Dose?",
            "9 - Quanto tempo sua amada demora para fazer mais que qualquer ooutra pessoa na sua vida, na música Te Assumi Pro Brasil interpretada por Matheus e Kauan?",
            "10 - Segundo a música 5Km interpretada por Henrique e Juliano, quantas esquinas são para o cara chegar até sua amada?"

    };

    private String respostas [][] = {
            {"Dá 50 Reais!", "Nunca ser amada de verdade!", "Levar algumas bofetadas!", "Casar-se um dia!"},
            {"Traição!", "Fazer de tudo por alguém", "Deixar o ar condicionado no 15!", "De não arrumar a cama que fizeram amor!"},
            {"Casar-se com ele!", "Processa ele!", "Expulsa-o de seu coração!", "Dá 50 Reais pra ele!"},
            {"O nome daquela pessoa!", "Te amo pra sempre!", "Cê acredita!", "Tic Tac, tic tac!"},
            {"Simplismente vai embora!", "Perdoa!", "Expulsa-o de seu coração!", "Dá 50 Reais!"},
            {"Tic Tac, tic tac!", "Te amo pra sempre!", "Joga-la fora", "O nome daquela pessoa!"},
            {"Casar-se com ele!", "Deixar o ar condicionado no 15!", "Dá 50 Reais!", "perdoá-la só mais uma vez!"},
            {"A sétima dose!", "Premeira dose!", "Oitava dose!", "A útilma dose!"},
            {"Dá 50 Reais!", "Menos de um mês!", "A sétima dose!", "Casar-se um dia!"},
            {"20 esquinas!", "2 esquinas!", "15 esquinas!", "Dá 50 Reais!"}
    };

    private String respostasCertas [] = {
            "Nunca ser amada de verdade!",
            "Traição!",
            "Expulsa-o de seu coração!",
            "O nome daquela pessoa!",
            "Dá 50 Reais!",
            "Jogá-la fora",
            "perdoá-la só mais uma vez!",
            "A sétima dose!",
            "Menos de um mês!",
            "20 esquinas!"

    };

    public String getPergunta (int a){
        String pergunta = perguntas[a];
        return pergunta;
    }

    public String getResposta1 (int a){
        String resposta1 = respostas[a][0];
        return resposta1;
    }

    public String getResposta2 (int a){
        String resposta2 = respostas[a][1];
        return resposta2;
    }

    public String getResposta3 (int a){
        String resposta3 = respostas[a][2];
        return resposta3;
    }

    public String getResposta4 (int a){
        String resposta4 = respostas[a][3];
        return resposta4;
    }

    public String getRespostaCerta (int a){
        String respostaCerta = respostasCertas[a];
        return respostaCerta;
    }

}
