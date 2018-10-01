package coutinhodeveloper.com.coutinmessenger.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import coutinhodeveloper.com.coutinmessenger.R;
import coutinhodeveloper.com.coutinmessenger.application.ConfiguracaoFirebase;
import coutinhodeveloper.com.coutinmessenger.helper.Base64Custom;
import coutinhodeveloper.com.coutinmessenger.helper.Preferencias;
import coutinhodeveloper.com.coutinmessenger.model.Mensagem;

public class ChatActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editMensagem;
    private ImageButton btMensagem;
    private DatabaseReference firebase;


    //destinatario
    private String nomeUsuarioDestinatario;
    private String idUsuarioDestinatario;

    //remetente
    private String idUsuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        toolbar = findViewById(R.id.tb_chat);
        editMensagem = findViewById(R.id.edit_mensagem);
        btMensagem = findViewById(R.id.bt_enviar);
        // recuperando dados usuario logado
        Preferencias preferencias = new Preferencias(ChatActivity.this);
        idUsuarioLogado = preferencias.getIdentificador();
        //recuperar dados enviados na intent
        Bundle extra = getIntent().getExtras();
        if (extra != null){
            //Recuperar dados do contato(destinatario)
            nomeUsuarioDestinatario = extra.getString("nome");
            idUsuarioDestinatario = Base64Custom.converterBase64(extra.getString("email"));


        }
        //cfg toolbar
        toolbar.setTitle(nomeUsuarioDestinatario);
        toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
        setSupportActionBar(toolbar);

        // sending message
        btMensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoMensagem = editMensagem.getText().toString();
                if (textoMensagem.isEmpty()){
                    Toast.makeText(ChatActivity.this,"Digite alguma mensagem!",Toast.LENGTH_LONG).show();

                }else { //saving msg on firebase
                    Mensagem mensagem = new Mensagem();
                    mensagem.setIdUsuario(idUsuarioLogado);
                    mensagem.setIdMensagem(textoMensagem);
                    salvarMensagemFirebase(idUsuarioLogado, idUsuarioDestinatario, mensagem);

                    //apagar msg após enviado
                    editMensagem.setText("");

                }
            }
        });

    }

    private Boolean salvarMensagemFirebase(String idRemetente, String idDestinatario, Mensagem mensagem){
        try{
            firebase = ConfiguracaoFirebase.getFirebase().child("mensagens");
            firebase.child(idRemetente)
                    .child(idDestinatario)
                    .push()
                    .setValue(mensagem);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

}
