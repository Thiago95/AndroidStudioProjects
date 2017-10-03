package com.thiago.calculadorath;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActvtMainCalc extends AppCompatActivity{

    TextView txtView;
    String atualString = "0.0", anteriorString = null;
    boolean flag = false;
    int atualOperacao = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvt_main_calc);

        txtView=(TextView)findViewById(R.id.txtView);

        int numeros[] = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_8};
        NmrBtnClickListener nmrBtnClickListener = new NmrBtnClickListener();
        for(int id : numeros){
            View v = findViewById(id);
            v.setOnClickListener(nmrBtnClickListener);
        }

        int operacao[] = {R.id.btn_soma, R.id.btn_sub, R.id.btn_div, R.id.btn_mult, R.id.btn_ptn, R.id.btn_limpar, R.id.btn_igual};
        OperacaoBtnClickListener operacaoBtnClickListener = new OperacaoBtnClickListener();
        for(int id : operacao) {
            View v = findViewById(id);
            v.setOnClickListener(operacaoBtnClickListener);
        }
        setAtualString("0.0");
    }

    public void setAtualString(String s) {
        atualString = s;
        txtView.setText(s);
    }

    class NmrBtnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if(flag) {
                anteriorString = atualString;
                atualString = "0.0";
                flag = false;
            }
            String text = (String)((Button)v).getText();
            if(atualString.equals("0.0")) setAtualString(text);
            else setAtualString(atualString + text);
        }
    }
    class OperacaoBtnClickListener implements View.OnClickListener {
    
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if(id == R.id.btn_limpar) {
                flag = false;
                setAtualString("0.0");
                anteriorString = null;
            }
            if(id == R.id.btn_ptn) if(!atualString.contains("."))
                setAtualString(atualString + ".");
            if(id == R.id.btn_soma || id == R.id.btn_soma || id == R.id.btn_soma || id == R.id.btn_soma) {
                atualOperacao = id;
                anteriorString = atualString;
                flag = true;
            }
            if(id == R.id.btn_mult || id == R.id.btn_mult || id == R.id.btn_mult || id == R.id.btn_mult) {
                atualOperacao = id;
                anteriorString = atualString;
                flag = true;
            }
            if(id == R.id.btn_sub || id == R.id.btn_sub || id == R.id.btn_sub || id == R.id.btn_sub) {
                atualOperacao = id;
                anteriorString = atualString;
                flag = true;
            }
            if(id == R.id.btn_div || id == R.id.btn_div || id == R.id.btn_div || id == R.id.btn_div) {
                atualOperacao = id;
                anteriorString = atualString;
                flag = true;
            }
            if(id == R.id.btn_igual) {
                double curr = Double.parseDouble(atualString);
                double result = 0;
                if(anteriorString != null) {
                    double prev = Double.parseDouble(anteriorString);
                    switch(atualOperacao) {
                        case R.id.btn_soma :
                            result = prev + curr;
                            break;
                        case R.id.btn_sub :
                            result = prev - curr;
                            break;
                        case R.id.btn_mult :
                            result = prev * curr;
                            break;
                        case R.id.btn_div :
                            result = prev / curr;
                            break;
                    }
                }
                setAtualString(Double.toString(result));
            }
        }
    }

}
