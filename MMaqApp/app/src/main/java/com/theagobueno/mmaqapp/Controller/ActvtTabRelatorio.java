package com.theagobueno.mmaqapp.Controller;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.theagobueno.mmaqapp.R;

public class ActvtTabRelatorio extends Fragment {
    private static final String TAG = "ActvtTabRelatorio";

    private TextView txtViewPdr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.actvt_tab_relatorio, container, false);
        txtViewPdr = (TextView) rootView.findViewById(R.id.txtViewPdr);

        txtViewPdr.setText("Actvt Tab Relatorio");

        return rootView;
    }
}
