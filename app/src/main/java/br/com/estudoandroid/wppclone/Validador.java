package br.com.estudoandroid.wppclone;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class Validador extends Activity {

    private EditText editCodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validador);

        editCodigo = (EditText) findViewById(R.id.codigoId);

        SimpleMaskFormatter smfCodigo = new SimpleMaskFormatter("NNNN");
        MaskTextWatcher mtwCodigo = new MaskTextWatcher(editCodigo,smfCodigo);

        editCodigo.addTextChangedListener(mtwCodigo);

    }
}
