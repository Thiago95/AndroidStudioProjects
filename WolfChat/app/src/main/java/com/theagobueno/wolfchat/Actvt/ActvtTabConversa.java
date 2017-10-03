package com.theagobueno.wolfchat.Actvt;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theagobueno.wolfchat.R;

public class ActvtTabConversa extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.actvt_tab_conversa, container, false);
        return rootView;
    }
}
