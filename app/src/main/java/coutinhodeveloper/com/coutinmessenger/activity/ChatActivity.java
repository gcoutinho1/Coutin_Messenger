package coutinhodeveloper.com.coutinmessenger.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import coutinhodeveloper.com.coutinmessenger.R;
import coutinhodeveloper.com.coutinmessenger.adapter.ChatAdapter;
import coutinhodeveloper.com.coutinmessenger.application.ConfiguracaoFirebase;
import coutinhodeveloper.com.coutinmessenger.helper.Base64Custom;
import coutinhodeveloper.com.coutinmessenger.helper.Preferencias;
import coutinhodeveloper.com.coutinmessenger.model.Mensagem;

/** Created by Guilherme Coutinho
 *  on 01/10/2018
 */

public class ChatActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editMensagem;
    private ImageButton btMensagem;
    private DatabaseReference firebase;
    private ListView listView;
    private ArrayAdapter<Mensagem> arrayAdapter;
    private ArrayList<Mensagem> mensagens;
    private ValueEventListener valueEventListenerChat;


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
        listView = findViewById(R.id.lv_chat);

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

        // montagem da listview e adapter
        mensagens = new ArrayList<>();
        /*mensagens.add("mensagem1");
        mensagens.add("mensagem2");
        arrayAdapter = new ArrayAdapter<String>(
                ChatActivity.this,
                android.R.layout.simple_list_item_1,
                mensagens
        ); */
        arrayAdapter = new ChatAdapter(ChatActivity.this,mensagens);
        listView.setAdapter(arrayAdapter);

        // recuperando msg do firebase
        firebase = ConfiguracaoFirebase.getFirebase()
                .child("mensagens")
                .child(idUsuarioLogado)
                .child(idUsuarioDestinatario);

        // criando listener para mensagens
        valueEventListenerChat = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //limpar arraylist de msg
                mensagens.clear();
                //recuperando as msg
                for (DataSnapshot dados : dataSnapshot.getChildren()){
                    //recuperar msg individual
                    Mensagem mensagem = dados.getValue(Mensagem.class);
                    //adicionando na lista chat
                    mensagens.add(mensagem);

                }

                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        firebase.addValueEventListener(valueEventListenerChat);


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
                    //salvando msg do remetente
                    salvarMensagemFirebase(idUsuarioLogado, idUsuarioDestinatario, mensagem);

                    //salvando msg do destinatario
                    salvarMensagemFirebase(idUsuarioDestinatario,idUsuarioLogado, mensagem);

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
