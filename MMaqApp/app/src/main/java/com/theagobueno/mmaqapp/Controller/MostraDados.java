package com.theagobueno.mmaqapp.Controller;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.theagobueno.mmaqapp.Entidades.Funcionario;
import com.theagobueno.mmaqapp.Entidades.Maquinario;
import com.theagobueno.mmaqapp.R;

import java.util.ArrayList;
import java.util.List;

public class MostraDados extends AppCompatActivity {

    private ListView listView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_dados);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final Integer opcList = bundle.getInt("opcao");

        listView = (ListView) findViewById(R.id.listaDados);

        selecionaLista(opcList);

    }

    public void selecionaLista(Integer opcao){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final Integer opcSelect = bundle.getInt("opcSelect");

        switch (opcao){
            case 1:
                listaMaquinario(opcSelect);
                break;
            case 2:
                listaManutencao(opcSelect);
                break;
            case 3:
                listaFuncionario(opcSelect);
                break;
            default:
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle("Aviso");
                dlg.setMessage("Entrada inválida, opção não recebeu opçã válida");
                dlg.setNeutralButton("OK", null);
                dlg.show();
        }
    }

    public void listaMaquinario(Integer opcao){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        opcao = 3;
        switch (opcao){
            case 1:
                Query orderMarca = databaseReference.child("maquinario").orderByChild("marca");
                maq(orderMarca);
                break;
            case 2:
                Query orderModelo= databaseReference.child("maquinario").orderByChild("modelo");
                maq(orderModelo);
                break;
            case 3:
                Query orderData = databaseReference.child("maquinario").orderByChild("dataAquisicao");
                maq(orderData);
                break;
            case 4:
                Query orderValor = databaseReference.child("maquinario").orderByChild("valorAquisicao");
                maq(orderValor);
                break;
            default:
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle("Aviso");
                dlg.setMessage("Entrada inválida, opção não recebeu opçã válida");
                dlg.setNeutralButton("OK", null);
                dlg.show();
        }
    }

    private void maq(Query order) {
        order.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> maquinario = new ArrayList<>();
                maquinario.clear();
                for (DataSnapshot objDataSnapshot:dataSnapshot.getChildren()){
                    Maquinario m = objDataSnapshot.getValue(Maquinario.class);
                    maquinario.add("--------\n|Marca: " + m.getMarca()+
                            "\n|Modelo: " + m.getModelo() +
                            "\n|Tipo de Máquina: " + m.getTipoMaquina() +
                            "\n|Potência: " + String.valueOf(m.getPotencia())+
                            "\n|Valor da Màquina: " + m.getValorAquisicao()+
                            "\n|Data da Compra: " + m.getDataAquisicao()+"\n--------"
                    );
                }
                ArrayAdapter<String> maquinarioArrayAdapter = new ArrayAdapter<String>(MostraDados.this, android.R.layout.simple_list_item_1, maquinario);
                listView.setAdapter(maquinarioArrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void listaFuncionario(Integer opcao) {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        opcao = 1;
        switch (opcao){
            case 1:
                Query orderNome = databaseReference.child("usuario").orderByChild("nome");
                func(orderNome);
                break;
            case 2:
                Query orderDataAdmissao = databaseReference.child("usuario").orderByChild("dataAdmissao");
                func(orderDataAdmissao);
                break;
            case 3:
                Query orderEmail = databaseReference.child("usuario").orderByChild("email");
                func(orderEmail);
                break;
            case 4:
                Query orderCnh = databaseReference.child("usuario").orderByChild("nmrRegistroCNH");
                func(orderCnh);
                break;
            case 5:
                Query orederCpf = databaseReference.child("usuario").orderByChild("cpf");
                func(orederCpf);
                break;
            default:
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle("Aviso");
                dlg.setMessage("Entrada inválida, opção não recebeu opçã válida");
                dlg.setNeutralButton("OK", null);
                dlg.show();
        }
    }

    public void func(Query order){
        order.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> funcioraioList = new ArrayList<>();
                funcioraioList.clear();
                for (DataSnapshot objDataSnapshot:dataSnapshot.getChildren()){
                    Funcionario f = objDataSnapshot.getValue(Funcionario.class);

                    funcioraioList.add("Nome: " + f.getNome());
                    funcioraioList.add("Endereço: " + f.getEnderco());
                    funcioraioList.add("Data da Adimissão: " + f.getDataAdmissao());
                    funcioraioList.add("E-mail: " + f.getEmail());
                    funcioraioList.add("Telefone: " + f.getTelefone());
                    funcioraioList.add("CPF: " + f.getCpf());
                    funcioraioList.add("CNH: " + f.getNmrRegistroCNH());
                    funcioraioList.add("Senha: " + f.getSenha());
                }
                ArrayAdapter<String> funcionarioArrayAdapter = new ArrayAdapter<String>(MostraDados.this, android.R.layout.simple_list_item_1, funcioraioList);
                listView.setAdapter(funcionarioArrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void listaManutencao(Integer opcao) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        switch (opcao){
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            default:
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle("Aviso");
                dlg.setMessage("Entrada inválida, opção não recebeu opçã válida");
                dlg.setNeutralButton("OK", null);
                dlg.show();
        }
    }

}
