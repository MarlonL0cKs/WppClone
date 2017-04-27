package br.com.estudoandroid.wppclone.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marlon on 23/04/2017.
 * Verifica a versão do SDK android e valida as permissões
 */

public class Permissoes {
    public static boolean validaPermissoes(int requestCode,Activity activity,String[] permissoes){

        if(Build.VERSION.SDK_INT >= 23){
            List<String> lista = new ArrayList<String>();
            for (String permissao : permissoes){
                Boolean valida = ContextCompat.checkSelfPermission(activity,permissao) == PackageManager.PERMISSION_GRANTED;

                if (!valida)lista.add(permissao);
            }
            if (lista.isEmpty()) return true;
            String[] arrayLista = new String[lista.size()];
            lista.toArray(arrayLista);

            ActivityCompat.requestPermissions(activity,arrayLista,requestCode);

        }

        return true;
    }
}
