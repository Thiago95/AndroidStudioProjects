package com.theagobueno.mmaqapp.Helper;

/**
 * Created by thiag on 18/12/2017.
 */

public class EstadoApp {
    private static EstadoApp singleInstance;

    private boolean isLoggingOut;

    private EstadoApp() {
    }

    public static EstadoApp getSingleInstance() {
        if (singleInstance == null) {
            singleInstance = new EstadoApp();
        }
        return singleInstance;
    }

    public boolean isLoggingOut() {
        return isLoggingOut;
    }

    public void setLoggingOut(boolean isLoggingOut) {
        this.isLoggingOut = isLoggingOut;
    }

}

