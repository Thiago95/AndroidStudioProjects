package com.thiago.bdconctar;

import java.sql.Connection;

/**
 * Created by thiag on 24/08/2017.
 */

public class DB extends _Default {

    private Connection conn;
    private String host = "localhost";
    private String db = "exemplo";
    private int porta = 3306;
    private String usuario = "root";
    private String senha = "";
    private String url = "jdbc:mysql://%s:%d/%s";
    public DB() {
        super();
        this.url = String.format(this.url, this.host, this.porta, this.db);
        //TODO abrir conexão
    }
}
