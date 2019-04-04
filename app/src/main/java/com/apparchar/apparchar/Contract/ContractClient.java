package com.apparchar.apparchar.Contract;

import android.content.Context;
import android.text.Editable;

import org.json.JSONException;

import java.util.ArrayList;

public interface ContractClient {

    interface View{
        void showResult(String info);
        void swap();
    }
    interface Presenter{
        void enviar(String nombre, String apellido, String edad, String correo, String cel, String user, String pass, String pass2) throws JSONException;
    }
}
