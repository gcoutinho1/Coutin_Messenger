package coutinhodeveloper.com.coutinmessenger.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


import java.util.Map;

import coutinhodeveloper.com.coutinmessenger.R;
import coutinhodeveloper.com.coutinmessenger.application.ConfiguracaoFirebase;
import coutinhodeveloper.com.coutinmessenger.model.Usuario;

/** Created by Guilherme Coutinho
 *  on 17/09/2018
 */

public class CadastroUsuarioactivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText senha;
    private Button botaoCadastrar;
    private Usuario usuario;

    private Firebase firebase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuarioactivity);

        nome = findViewById(R.id.edit_cadastro_nome);
        email = findViewById(R.id.edit_cadastro_email);
        senha = findViewById(R.id.edit_cadastro_senha);
        botaoCadastrar = findViewById(R.id.botao_cadastrar);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usuario = new Usuario();
                usuario.setNome( nome.getText().toString() );
                usuario.setEmail( email.getText().toString() );
                usuario.setSenha( senha.getText().toString() );
                cadastrarUsuario();

            }
        });
    }

    private void cadastrarUsuario(){

        firebase = ConfiguracaoFirebase.getFirebase();
        firebase.createUser(
                usuario.getEmail(),
                usuario.getSenha(),
                new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> stringObjectMap) {
                        Toast.makeText(CadastroUsuarioactivity.this, "Sucesso ao cadastrar",Toast.LENGTH_LONG).show();
                        usuario.setId(stringObjectMap.get("uid").toString());
                        usuario.salvar();

                        finish();


                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Toast.makeText(CadastroUsuarioactivity.this, firebaseError.getMessage(),Toast.LENGTH_LONG).show();

                    }
                }

        );

    }
}
