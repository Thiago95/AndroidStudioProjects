package com.theagobueno.atividadeframeworks;

import junit.framework.TestCase;

/**
 * Created by thiag on 01/12/2017.
 */

public class CRUDTeste extends TestCase{

    public void testUpdateFalse(){
        CRUD crud = new CRUD();
        crud.save(null);
        boolean result = crud.update(0, null);
        assertFalse(result);
    }

    public void testUpdateTrue(){
        CRUD crud = new CRUD();
        crud.save("thiago");
        boolean result = crud.update(0, "thiago");
        assertTrue(result);
    }


}
