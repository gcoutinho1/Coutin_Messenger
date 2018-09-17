package coutinhodeveloper.com.coutinmessenger.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;

import coutinhodeveloper.com.coutinmessenger.R;
import coutinhodeveloper.com.coutinmessenger.helper.Preferencias;

/** Created by Guilherme Coutinho
 *  on 16/09/2018
 */

public class ValidadorActivity extends AppCompatActivity {

    private EditText codValidacao;
    private Button validar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validador);

        codValidacao = findViewById(R.id.edit_cod_validacao);
        validar = findViewById(R.id.botao_validar);

        SimpleMaskFormatter simpleMaskCodValidacao = new SimpleMaskFormatter("NNNN");
        MaskTextWatcher maskCodValidacao = new MaskTextWatcher(codValidacao,simpleMaskCodValidacao);

        codValidacao.addTextChangedListener(maskCodValidacao);
        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // recuperar dados pref usuarios
                Preferencias preferencias = new Preferencias(ValidadorActivity.this);
                HashMap<String,String> usuario = preferencias.getDadosUsuario();

                String tokenGerado = usuario.get("token");
                String tokenDigitado = codValidacao.getText().toString();
                    if (tokenDigitado.equals(tokenGerado)){
                        Toast.makeText(ValidadorActivity.this,"CÓDIGO VALIDADO",Toast.LENGTH_LONG).show();

                    }else {
                        Toast.makeText(ValidadorActivity.this,"CÓDIGO INVALIDO",Toast.LENGTH_LONG).show();

                    }

            }
        });
    }
}
