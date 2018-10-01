package coutinhodeveloper.com.coutinmessenger.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


import coutinhodeveloper.com.coutinmessenger.R;
import coutinhodeveloper.com.coutinmessenger.application.ConfiguracaoFirebase;

/** Created by Guilherme Coutinho
 *  on 15/09/2018
 */

public class MainActivity extends AppCompatActivity {

    private DatabaseReference firebase;
    private FirebaseAuth firebaseAuth;
    private Button botaoSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebase = ConfiguracaoFirebase.getFirebase();
        firebaseAuth = FirebaseAuth.getInstance();
        botaoSair = findViewById(R.id.botao_sair);
        botaoSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


        /*
        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://coutinmessenger.firebaseio.com/mensagens");
        firebase.setValue("teste do firebase"); */

    }
}
