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
import android.widget.Toast;

import com.theagobueno.mmaqapp.R;

public class ActvtTabFuncionario extends Fragment {
    private static final String TAG = "ActvtTabFuncionario";

    private TextView txtViewPdr;
    private FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.actvt_tab_funcionario, container, false);
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        txtViewPdr = (TextView) rootView.findViewById(R.id.txtViewPdr);
        txtViewPdr.setText("Actvt Tab Funcionario");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CadastroFuncionarioActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

}
