package coutinhodeveloper.com.coutinmessenger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;

/** Created by Guilherme Coutinho
 *  on 15/09/2018
 */

public class MainActivity extends AppCompatActivity {

    private Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://coutinmessenger.firebaseio.com/mensagens");
        firebase.setValue("teste do firebase");

    }
}
