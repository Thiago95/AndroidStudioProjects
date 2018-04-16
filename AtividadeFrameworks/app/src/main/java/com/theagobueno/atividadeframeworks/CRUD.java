package com.theagobueno.atividadeframeworks;

import java.util.ArrayList;

/**
 * Created by thiag on 01/12/2017.
 */

public class CRUD {

    private ArrayList<String> name = new ArrayList<>();

    public void save(String nome){
        name.add(nome);

    }

    public ArrayList<String> getName() {
        return name;
    }

    public boolean update(int position, String newName){
        try{
            name.remove(position);
            name.add(position, newName);
            return true;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int position){
        try{
            name.remove(position);
            return true;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
