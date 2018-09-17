package coutinhodeveloper.com.coutinmessenger.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;
import java.util.Random;

import coutinhodeveloper.com.coutinmessenger.R;
import coutinhodeveloper.com.coutinmessenger.helper.Permissao;
import coutinhodeveloper.com.coutinmessenger.helper.Preferencias;

/** Created by Guilherme Coutinho
 *  on 16/09/2018
 */

public class LoginActivity extends AppCompatActivity {

    /*private EditText telefone;
    private EditText nome;
    private EditText codPais;
    private EditText codArea;
    private Button cadastrar;
    private String[] permissoesNecessarias = new String[]{
                Manifest.permission.SEND_SMS,
                Manifest.permission.INTERNET,

    }; */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        /*Permissao.validaPermissoes(1,this,permissoesNecessarias);

        telefone = findViewById(R.id.edit_telefone);
        nome = findViewById(R.id.edit_nome);
        codPais = findViewById(R.id.edit_cod_pais);
        codArea = findViewById(R.id.edit_cod_area);
        cadastrar = findViewById(R.id.botao_cadastrar);

        SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter("NNNNN-NNNN");
        SimpleMaskFormatter simpleMaskCodPais = new SimpleMaskFormatter("+NN");
        SimpleMaskFormatter simpleMaskCodArea = new SimpleMaskFormatter("NN");

        MaskTextWatcher maskTelefone = new MaskTextWatcher(telefone, simpleMaskTelefone);
        MaskTextWatcher maskCodPais = new MaskTextWatcher(codPais, simpleMaskCodPais);
        MaskTextWatcher maskCodArea = new MaskTextWatcher(codArea, simpleMaskCodArea);

        telefone.addTextChangedListener( maskTelefone );
        codPais.addTextChangedListener( maskCodPais );
        codArea.addTextChangedListener( maskCodArea );

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomeUsuario = nome.getText().toString();
                String telefoneCompleto =
                        codPais.getText().toString() +
                        codArea.getText().toString() +
                        telefone.getText().toString();

                String telefoneNaoFormatado = telefoneCompleto.replace("+", "");
                telefoneNaoFormatado = telefoneCompleto.replace("-","");

                // geração de token
                Random rng = new Random();
                int numeroRng = rng.nextInt(9999 - 1000) + 1000;
                String token = String.valueOf( numeroRng );
                String mensagemEnvio = "CoutinMessenger código de confirmação: " + token;

                //salvando os dados para validação
                Preferencias preferencias = new Preferencias(LoginActivity.this);
                preferencias.salvarUsuarioPreferencia(nomeUsuario,telefoneNaoFormatado,token);

                // envio de sms
                 telefoneNaoFormatado = "8135";
                 boolean enviadoSMS = enviaSMS("+" + telefoneNaoFormatado, mensagemEnvio);
                    if (enviadoSMS ){

                        Intent intent = new Intent(LoginActivity.this,ValidadorActivity.class);
                        startActivity(intent);
                        finish();

                    }else {
                        Toast.makeText(LoginActivity.this,"Problema ao enviar SMS, tente novamente",Toast.LENGTH_LONG).show();
                    }




                /*
                HashMap<String, String> usuario = preferencias.getDadosUsuario();
                Log.i("nome", "n:" + usuario.get("nome") + "FONE:" + usuario.get("telefone") + "token" + usuario.get("token"));






            }
        });


    }

    private boolean enviaSMS(String telefone, String mensagem){

        try{

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(telefone, null, mensagem, null, null);
            return  true;

        }catch (Exception e){
            e.printStackTrace();
            return false;

        }


    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){

        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        for (int resultado : grantResults){

            if (resultado == PackageManager.PERMISSION_DENIED){
                alertaValidacaoPermissao();

            }

        }

    }

    private void alertaValidacaoPermissao(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões negadas");
        builder.setMessage("Para utilizar o app, é necessario aceitar as permissões");
        builder.setPositiveButton("confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show(); */
    }

    public void abrirCadastroUsuario(View view){
        Intent intent = new Intent(LoginActivity.this,CadastroUsuarioactivity.class);
        startActivity(intent);
    }
}
