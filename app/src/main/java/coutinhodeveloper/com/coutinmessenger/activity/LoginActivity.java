package coutinhodeveloper.com.coutinmessenger.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


import coutinhodeveloper.com.coutinmessenger.R;
import coutinhodeveloper.com.coutinmessenger.application.ConfiguracaoFirebase;
import coutinhodeveloper.com.coutinmessenger.model.Usuario;


/** Created by Guilherme Coutinho
 *  on 16/09/2018
 */

public class LoginActivity extends AppCompatActivity {

    //private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private Firebase firebase;
    private EditText email;
    private EditText senha;
    private Button botaoLogar;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //databaseReference = ConfiguracaoFirebase.getFirebase();
        firebase = ConfiguracaoFirebase.getFirebase();
        email = findViewById(R.id.edit_login_email);
        senha = findViewById(R.id.edit_login_senha);
        botaoLogar = findViewById(R.id.botao_logar);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());
                validarLogin();

            }
        });




    }

    public void abrirCadastroUsuario(View view){

        Intent intent = new Intent(LoginActivity.this,CadastroUsuarioactivity.class);
        startActivity(intent);
    }

    private void validarLogin(){

        firebase.authWithPassword(
                usuario.getEmail(),
                usuario.getSenha(),
                new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        abrirTelaPrincipal();

                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {

                        Toast.makeText(LoginActivity.this, firebaseError.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    private void abrirTelaPrincipal(){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
