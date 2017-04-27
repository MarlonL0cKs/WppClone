package br.com.estudoandroid.wppclone;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.estudoandroid.wppclone.helper.Permissoes;

public class MainActivity extends Activity implements View.OnClickListener {

    private String[] permissoesSolicitadas = new String[]{
            Manifest.permission.SEND_SMS,
    };
// Arquivo editado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Permissoes.validaPermissoes(1,MainActivity.this,permissoesSolicitadas);



        Button login = (Button) findViewById(R.id.activityLogin);
        Button validacao = (Button) findViewById(R.id.activityValidacao);

        login.setOnClickListener(this);
        validacao.setOnClickListener(this);

        }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activityLogin:
                startActivity(new Intent(getApplicationContext(), Login.class));
                break;
            case R.id.activityValidacao:
                startActivity(new Intent(getApplicationContext(), Validador.class));
                break;
        }
    }
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResoults){

        super.onRequestPermissionsResult(requestCode,permissions,grantResoults);
        for (int result : grantResoults){
            if (result == PackageManager.PERMISSION_DENIED){
                alertaPermissao();
            }
        }

    }
    private void alertaPermissao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atenção");
        builder.setMessage("Em seguida, favor ACEITAR as permissões solicitadas!!!");
        builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Permissoes.validaPermissoes(1, MainActivity.this, permissoesSolicitadas);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

