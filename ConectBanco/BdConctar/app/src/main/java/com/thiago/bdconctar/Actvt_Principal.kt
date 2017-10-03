package com.thiago.bdconctar

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View

class Actvt_Principal : AppCompatActivity() {

    private var lstDados: RecyclerView? = null
    private var fab: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvt__principal)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        fab = findViewById(R.id.fab) as FloatingActionButton
        lstDados = findViewById(R.id.lstDados) as RecyclerView
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(Actvt_Principal.this, ActvtCadCli.class);
                startActivity(it);

            }
        });*/
    }

    fun cadastrar(view: View) {

        val it = Intent(this@Actvt_Principal, ActvtCadCli::class.java)
        startActivity(it)


    }

}
