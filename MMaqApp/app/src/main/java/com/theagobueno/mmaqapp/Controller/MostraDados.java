package com.theagobueno.mmaqapp.Controller;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.theagobueno.mmaqapp.Entidades.Funcionario;
import com.theagobueno.mmaqapp.Entidades.Manutencao;
import com.theagobueno.mmaqapp.Entidades.Maquinario;
import com.theagobueno.mmaqapp.R;

import java.util.ArrayList;
import java.util.List;

public class MostraDados extends AppCompatActivity {

    private ListView listView;
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference;
    List<String> listSelect = new ArrayList<>();
    private ClipboardManager myClipboard;
    private ClipData myClip;
    //final Integer opc = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_dados);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final Integer opcList = bundle.getInt("opcao");
        selecionaLista(opcList);
        listView = (ListView) findViewById(R.id.listaDados);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selecionaTexto(listSelect.get(position));
                return true;
            }
        });
    }

    public void selecionaTexto(String textSelect){
        int sdk_Version = android.os.Build.VERSION.SDK_INT;
        if(sdk_Version < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(textSelect.toString());   // Assuming that you are copying the text from a TextView
            Toast.makeText(getApplicationContext(), "Copied to Clipboard!", Toast.LENGTH_SHORT).show();
        }
        else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Text Label", textSelect.toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(getApplicationContext(), "Copied to Clipboard!", Toast.LENGTH_SHORT).show();
        }
    }

    public void selecionaLista(Integer opcao){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        try{
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
                case 5:
                    Query orderNome = databaseReference.child("usuario").orderByChild("nome");
                    func(orderNome);
                    break;
                case 6:
                    Query orderDataAdmissao = databaseReference.child("usuario").orderByChild("dataAdmissao");
                    func(orderDataAdmissao);
                    break;
                case 7:
                    Query orderEmail = databaseReference.child("usuario").orderByChild("email");
                    func(orderEmail);
                    break;
                case 8:
                    Query orderCnh = databaseReference.child("usuario").orderByChild("nmrRegistroCNH");
                    func(orderCnh);
                    break;
                case 9:
                    Query orederCpf = databaseReference.child("usuario").orderByChild("cpf");
                    func(orederCpf);
                    break;
                case 10:
                    Query orderMaq = databaseReference.child("manutecao").orderByChild("idMaquinario");
                    man(orderMaq);
                    break;
                case 11:
                    Query orderTipoMan = databaseReference.child("manutecao").orderByChild("tipoManutencao");
                    man(orderTipoMan);
                    break;
                case 12:
                    Query orderDtMan = databaseReference.child("manutecao").orderByChild("dataAtualManutencao");
                    man(orderDtMan);
                    break;
                case 13:
                    Query orderCustoMan = databaseReference.child("manutecao").orderByChild("custoManutencao");
                    man(orderCustoMan);
                    break;
                default:
                    AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                    dlg.setTitle("Aviso");
                    dlg.setMessage("Entrada inválida, opção não recebeu opçã válida");
                    dlg.setNeutralButton("OK", null);
                    dlg.show();
            }
        }catch (Exception e){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage("Algo de errado nao esta certo nessa bagaça");
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
                listSelect.clear();
                for (DataSnapshot objDataSnapshot:dataSnapshot.getChildren()){
                    Maquinario m = objDataSnapshot.getValue(Maquinario.class);
                    maquinario.add("+--------\n|Marca: " + m.getMarca()+
                            "\n|Modelo: " + m.getModelo() +
                            "\n|Tipo de Máquina: " + m.getTipoMaquina() +
                            "\n|Potência: " + String.valueOf(m.getPotencia())+
                            "\n|Valor da Màquina: " + m.getValorAquisicao()+
                            "\n|Data da Compra: " + m.getDataAquisicao()+"\n+--------"
                    );
                    listSelect.add("+--------\n|Marca: " + m.getMarca()+
                            "\n|Modelo: " + m.getModelo() +
                            "\n|Tipo de Máquina: " + m.getTipoMaquina() +
                            "\n|Potência: " + String.valueOf(m.getPotencia())+
                            "\n|Valor da Màquina: " + m.getValorAquisicao()+
                            "\n|Data da Compra: " + m.getDataAquisicao()+"\n+--------");
                }
                ArrayAdapter<String> maquinarioArrayAdapter = new ArrayAdapter<String>(MostraDados.this, android.R.layout.simple_list_item_1, maquinario);
                listView.setAdapter(maquinarioArrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void func(Query order){
        order.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> funcioraioList = new ArrayList<>();
                funcioraioList.clear();
                listSelect.clear();
                for (DataSnapshot objDataSnapshot:dataSnapshot.getChildren()){
                    Funcionario f = objDataSnapshot.getValue(Funcionario.class);

                    funcioraioList.add("+--------\n|Nome: " + f.getNome() +
                            "\n|Endereço: " + f.getEnderco() +
                            "\n|Data da Adimissão: " + f.getDataAdmissao() +
                            "\n|Data da Adimissão: " + f.getDataAdmissao() +
                            "\n|E-mail: " + f.getEmail() +
                            "\n|Telefone: " + f.getTelefone() +
                            "\n|CPF: " + f.getCpf() +
                            "\n|CNH: " + f.getNmrRegistroCNH() + "\n+--------"
                    );
                    listSelect.add("+--------\n|Nome: " + f.getNome() +
                            "\n|Endereço: " + f.getEnderco() +
                            "\n|Data da Adimissão: " + f.getDataAdmissao() +
                            "\n|Data da Adimissão: " + f.getDataAdmissao() +
                            "\n|E-mail: " + f.getEmail() +
                            "\n|Telefone: " + f.getTelefone() +
                            "\n|CPF: " + f.getCpf() +
                            "\n|CNH: " + f.getNmrRegistroCNH() + "\n+--------");
                }
                ArrayAdapter<String> funcionarioArrayAdapter = new ArrayAdapter<String>(MostraDados.this, android.R.layout.simple_list_item_1, funcioraioList);
                listView.setAdapter(funcionarioArrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void man(Query order){
        order.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> manutencaoList = new ArrayList<>();
                manutencaoList.clear();
                listSelect.clear();
                for (DataSnapshot objDataSnapshot:dataSnapshot.getChildren()){
                    Manutencao man = objDataSnapshot.getValue(Manutencao.class);
                    manutencaoList.add("--------------\n"+
                            "|Máquina: \n| - "+man.getIdMaquinario()+
                            "\n|Tipo de Manutenção: \n| - "+man.getTipoManutencao()+
                            "\n|Manutenção Realizada Dia: \n| - "+ man.getDataAtualManutencao() +
                            "\n|Próxima Manutenção Deste Tipo: \n| - "+ man.getDataProximaMatencao() +
                            "\n|Custo da Manutenção: \n| - "+ man.getCustoManutencao()+
                            "\n--------------");
                    listSelect.add("--------------\n"+
                            "|Máquina: \n| - "+man.getIdMaquinario()+
                            "\n|Tipo de Manutenção: \n| - "+man.getTipoManutencao()+
                            "\n|Manutenção Realizada Dia: \n| - "+ man.getDataAtualManutencao() +
                            "\n|Próxima Manutenção Deste Tipo: \n| - "+ man.getDataProximaMatencao() +
                            "\n|Custo da Manutenção: \n| - "+ man.getCustoManutencao()+
                            "\n--------------");

                }
                ArrayAdapter<String> maquinarioArrayAdapter = new ArrayAdapter<String>(MostraDados.this, android.R.layout.simple_list_item_1, manutencaoList);
                listView.setAdapter(maquinarioArrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
