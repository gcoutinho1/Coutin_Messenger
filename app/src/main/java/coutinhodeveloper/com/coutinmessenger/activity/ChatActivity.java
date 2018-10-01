package coutinhodeveloper.com.coutinmessenger.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import coutinhodeveloper.com.coutinmessenger.R;

public class ChatActivity extends AppCompatActivity {

    private Toolbar toolbar;
    //destinatario
    private String nomeUsuarioDestinatario;

    //remetente

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        toolbar = findViewById(R.id.tb_chat);

        Bundle extra = getIntent().getExtras();
        if (extra != null){
            nomeUsuarioDestinatario = extra.getString("nome");


        }
        //cfg toolbar
        toolbar.setTitle(nomeUsuarioDestinatario);
        toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
        setSupportActionBar(toolbar);


    }

}
