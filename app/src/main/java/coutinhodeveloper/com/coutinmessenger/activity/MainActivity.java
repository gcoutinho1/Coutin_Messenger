package coutinhodeveloper.com.coutinmessenger.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebase = ConfiguracaoFirebase.getFirebase();
        firebaseAuth = FirebaseAuth.getInstance();

        toolbar = findViewById(R.id.toolbar_principal);
        toolbar.setTitle("Coutin Messenger");
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_sair :
                deslogarUsuario();
                return true;
            case R.id.action_settings :
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void deslogarUsuario(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();


    }
}
