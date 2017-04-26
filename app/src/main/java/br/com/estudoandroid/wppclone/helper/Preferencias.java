package br.com.estudoandroid.wppclone.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Marlon on 23/04/2017.
 */

public class Preferencias {

    private Context context;
    private SharedPreferences preferences;
    private final String NOME_ARQUIVO = "TOKEN";
    private SharedPreferences.Editor editor;
    public Preferencias(Context parametro){

        context = parametro;

        preferences = context.getSharedPreferences("TOKEN",Context.MODE_PRIVATE);
        editor = preferences.edit();

    }

    public void salvarPreferencias(String nome,String numero, String token){
        editor.putString("nome", nome);
        editor.putString("numero", numero);
        editor.putString("token", token);
        editor.commit();
    }
    public HashMap<String,String> getDadosUsuario(){
        HashMap<String,String> dadosUsuario = new HashMap<>();
        dadosUsuario.put("nome",preferences.getString("nome",null));
        dadosUsuario.put("telefone",preferences.getString("telefone",null));
        dadosUsuario.put("token",preferences.getString("token",null));

        return dadosUsuario;
    }
}
