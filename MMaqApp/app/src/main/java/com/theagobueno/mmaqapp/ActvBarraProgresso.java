package com.theagobueno.mmaqapp;

import android.content.Context;
import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.theagobueno.mmaqapp.Controller.LoginActivity;

public class ActvBarraProgresso extends AsyncTask<Object, Object, String> {

    private ProgressBar progressBar;
    private TextView texto;
    private int total = 0;
    private static int PROGRESSO = 25;

    public ActvBarraProgresso(Class<LoginActivity> context, ProgressBar progressBar, TextView texto) {
        this.progressBar = progressBar;
        this.texto = texto;
    }

    @Override
    protected void onPreExecute() {
        texto.setText("0%");
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Object... params) {
        try {

            Thread.sleep(1000);

            for (int i=0; i<4; i++) {
                publishProgress();
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        total += PROGRESSO;
        progressBar.incrementProgressBy(PROGRESSO);
        texto.setText(total + "%");

        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
        texto.setText("Tarefa concluída");
        texto.setGravity(Gravity.CENTER_HORIZONTAL);
        super.onPostExecute(result);
    }
}