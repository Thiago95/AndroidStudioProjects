package com.theagobueno.mmaqapp;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.google.android.gms.tasks.Task;
import com.theagobueno.mmaqapp.Controller.CadastroMaquinarioActivity;
import com.theagobueno.mmaqapp.Controller.DatePickerFragment;

import junit.framework.TestCase;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created by thiag on 19/02/2018.
 */

public class TesteCadMaq extends ActivityInstrumentationTestCase2{
    public EditText edtCadMaqMarc;
    public EditText edtCadMaqModel;
    public EditText edtCadMaqTip;
    public EditText edtCadMaqPotenci;
    public EditText edtCadMaqValorAquisica;
    public EditText edtCadMaqDataAquisica;

    private CadastroMaquinarioActivity cadMaqAct;

    private DatePickerFragment maskDate;

    Locale mLocale = new Locale("pt", "BR");

    public TesteCadMaq() {
        super("com.theagobueno.mmaqapp.controller", CadastroMaquinarioActivity.class);
    }



    public void testPreCondicoes(){
        edtCadMaqMarc         = (EditText)cadMaqAct.findViewById(R.id.edtCadMaqMarca);
        edtCadMaqModel         = (EditText)cadMaqAct.findViewById(R.id.edtCadMaqModelo);
        edtCadMaqTip         = (EditText)cadMaqAct.findViewById(R.id.edtCadMaqTipo);
        edtCadMaqPotenci       = (EditText)cadMaqAct.findViewById(R.id.edtCadMaqPotencia);
        edtCadMaqValorAquisica = (EditText)cadMaqAct.findViewById(R.id.edtCadMaqValorAquisicao);
        edtCadMaqValorAquisica.addTextChangedListener(new DatePickerFragment(edtCadMaqValorAquisica, mLocale));
        edtCadMaqDataAquisica = (EditText)cadMaqAct.findViewById(R.id.edtCadMaqDataAquisicao);
        edtCadMaqDataAquisica.addTextChangedListener(maskDate.insert("##/##/####", edtCadMaqDataAquisica));
        assertTrue(cadMaqAct != null);
        assertTrue(edtCadMaqMarc!= null);
        assertTrue(edtCadMaqModel!= null);
        assertTrue(edtCadMaqTip!= null);
        assertTrue(edtCadMaqPotenci!= null);
        assertTrue(edtCadMaqValorAquisica!= null);
        assertTrue(edtCadMaqDataAquisica!= null);
    }
    boolean res = false;
    public void testTrue(){



        testPreCondicoes();

        cadMaqAct.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                edtCadMaqMarc.setText("John Deere");
                edtCadMaqModel.setText("7225");
                edtCadMaqTip.setText("Traçado 4x4");
                edtCadMaqPotenci.setText("145");
                edtCadMaqValorAquisica.setText("");
                edtCadMaqDataAquisica.setText("21012018");
                try {
                    cadMaqAct.validaCampos();
                } catch (ParseException e) {
                    e.printStackTrace();
                    res = true;
                }
            }
        });
        /*edtCadMaqMarca.setText("John Deere");
        edtCadMaqModelo.setText("7225");
        edtCadMaqTipo.setText("Traçado 4x4");
        edtCadMaqPotencia.setText("145");
        edtCadMaqValorAquisicao.setText("110000");
        edtCadMaqDataAquisicao.setText("21012018");*/



        assertFalse(res);
    }

}
