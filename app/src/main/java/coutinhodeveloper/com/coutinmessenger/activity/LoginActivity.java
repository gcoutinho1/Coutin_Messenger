package coutinhodeveloper.com.coutinmessenger.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import coutinhodeveloper.com.coutinmessenger.R;

/** Created by Guilherme Coutinho
 *  on 16/09/2018
 */

public class LoginActivity extends AppCompatActivity {

    private EditText telefone;
    private EditText nome;
    private EditText codPais;
    private EditText codArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        telefone = findViewById(R.id.edit_telefone);
        nome = findViewById(R.id.edit_nome);
        codPais = findViewById(R.id.edit_cod_pais);
        codArea = findViewById(R.id.edit_cod_area);

        SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter("NNNNN-NNNN");
        SimpleMaskFormatter simpleMaskCodPais = new SimpleMaskFormatter("+NN");
        SimpleMaskFormatter simpleMaskCodArea = new SimpleMaskFormatter("NN");



        MaskTextWatcher maskTelefone = new MaskTextWatcher(telefone, simpleMaskTelefone);
        MaskTextWatcher maskCodPais = new MaskTextWatcher(codPais, simpleMaskCodPais);
        MaskTextWatcher maskCodArea = new MaskTextWatcher(codArea, simpleMaskCodArea);

        telefone.addTextChangedListener( maskTelefone );
        codPais.addTextChangedListener( maskCodPais );
        codArea.addTextChangedListener( maskCodArea );


    }
}
