package coutinhodeveloper.com.coutinmessenger.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


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

    public DatabaseReference firebase;
    private FirebaseAuth mAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuarioactivity);

        mAuth = FirebaseAuth.getInstance();

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

        //firebase = ConfiguracaoFirebase.getFirebase();
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
                ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //Toast.makeText(CadastroUsuarioactivity.this, "Sucesso ao cadastrar",Toast.LENGTH_LONG).show();
                if (!task.isSuccessful()){
                    Toast.makeText(CadastroUsuarioactivity.this, "Sucesso ao cadastrar",Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
