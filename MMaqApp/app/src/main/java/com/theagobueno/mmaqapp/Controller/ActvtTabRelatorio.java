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
import android.widget.Button;
import android.widget.TextView;

import com.theagobueno.mmaqapp.R;

public class ActvtTabRelatorio extends Fragment {

    private Button btnFuncionario;
    private Button btnMaquina;
    private Button btnManutencao;
    private Integer opcao = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.actvt_tab_relatorio, container, false);
        btnFuncionario = (Button) rootView.findViewById(R.id.btnFuncionario);
        btnMaquina = (Button) rootView.findViewById(R.id.btnMaquina);
        btnManutencao = (Button) rootView.findViewById(R.id.btnManutencao);

        btnFuncionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcao = 3;
                Intent intent = new Intent(getActivity(), MostraDados.class);
                Bundle bundle = new Bundle();
                bundle.putInt("opcao", opcao);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnMaquina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcao = 1;
                Intent intent = new Intent(getActivity(), MostraDados.class);
                Bundle bundle = new Bundle();
                bundle.putInt("opcao", opcao);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btnManutencao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcao = 2;
                Intent intent = new Intent(getActivity(), MostraDados.class);
                Bundle bundle = new Bundle();
                bundle.putInt("opcao", opcao);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
