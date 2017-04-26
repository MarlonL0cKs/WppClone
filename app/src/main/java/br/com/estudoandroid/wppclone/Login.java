package br.com.estudoandroid.wppclone;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;
import java.util.Random;

import br.com.estudoandroid.wppclone.helper.Permissoes;
import br.com.estudoandroid.wppclone.helper.Preferencias;


public class Login extends Activity {
    private EditText editNumero;
    private EditText editDdi;
    private Button botaoCad;
    private EditText editNome;
    private String telefoneSemFormatacao;
    private String telefoneCompleto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        editNumero = (EditText) findViewById(R.id.numeroId);
        editNome = (EditText) findViewById(R.id.editNome);
        editDdi = (EditText) findViewById(R.id.ddi);
        botaoCad = (Button) findViewById(R.id.botaoCadastrar);

        SimpleMaskFormatter smfNumero = new SimpleMaskFormatter("NN NNNNN-NNNN");
        SimpleMaskFormatter smfDdi = new SimpleMaskFormatter("+NN");

        MaskTextWatcher mtwNumero = new MaskTextWatcher(editNumero,smfNumero);
        MaskTextWatcher mtwDdi = new MaskTextWatcher(editDdi,smfDdi);

        editNumero.addTextChangedListener(mtwNumero);
        editDdi.addTextChangedListener(mtwDdi);

        botaoCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telefoneCompleto = editDdi.getText().toString()+
                editNumero.getText().toString();

                telefoneSemFormatacao = telefoneCompleto.replace("+","");
                telefoneSemFormatacao = telefoneSemFormatacao.replace(" ","");
                telefoneSemFormatacao = telefoneSemFormatacao.replace("-","");
                //Toast.makeText(getApplicationContext(),"Numero Completo: "+telefoneSemFormatacao,Toast.LENGTH_LONG).show();

        Random random = new Random();
        int nRandon = random.nextInt(9999 - 1000) + 1000;
        String token = String.valueOf(nRandon);

        Preferencias preferencias = new Preferencias(getApplicationContext());

        preferencias.salvarPreferencias(editNome.getText().toString(),telefoneSemFormatacao,token);

       // HashMap<String,String> usuario = preferencias.getDadosUsuario();
        String mensagemenvio="Codigo de Validação: "+token;

             //   telefoneSemFormatacao = "5554";
        enviaSMS("+"+telefoneSemFormatacao,mensagemenvio );

            }
        });
    }

    public boolean enviaSMS (String telefone, String mensagem){
        try{

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(telefone,null,mensagem,null,null);

            return true;

        }catch (Exception e){
            e.printStackTrace();

            return false;
        }
    }
}
