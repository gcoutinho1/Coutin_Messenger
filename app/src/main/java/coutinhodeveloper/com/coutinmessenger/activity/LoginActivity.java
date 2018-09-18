package coutinhodeveloper.com.coutinmessenger.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.firebase.client.Firebase;
import coutinhodeveloper.com.coutinmessenger.R;
import coutinhodeveloper.com.coutinmessenger.application.ConfiguracaoFirebase;


/** Created by Guilherme Coutinho
 *  on 16/09/2018
 */

public class LoginActivity extends AppCompatActivity {

    private Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebase = ConfiguracaoFirebase.getFirebase();




    }

    public void abrirCadastroUsuario(View view){
        Intent intent = new Intent(LoginActivity.this,CadastroUsuarioactivity.class);
        startActivity(intent);
    }
}
