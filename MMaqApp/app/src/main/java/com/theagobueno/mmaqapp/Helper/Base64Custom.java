package com.theagobueno.mmaqapp.Helper;

import android.util.Base64;

/**
 * Created by thiag on 05/10/2017.
 */

public class Base64Custom {

    public static String codificadorBase64 (String texto){
        return Base64.encodeToString(texto.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\r)", "");
    }

    public static String decoficarBase64 (String textoCodificado){
        return new String(Base64.decode(textoCodificado, Base64.DEFAULT));
    }

}
