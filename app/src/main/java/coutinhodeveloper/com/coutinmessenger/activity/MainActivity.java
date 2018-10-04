package coutinhodeveloper.com.coutinmessenger.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


import coutinhodeveloper.com.coutinmessenger.R;
import coutinhodeveloper.com.coutinmessenger.adapter.TabAdapter;
import coutinhodeveloper.com.coutinmessenger.application.ConfiguracaoFirebase;
import coutinhodeveloper.com.coutinmessenger.helper.Base64Custom;
import coutinhodeveloper.com.coutinmessenger.helper.Preferencias;
import coutinhodeveloper.com.coutinmessenger.helper.SlidingTabLayout;
import coutinhodeveloper.com.coutinmessenger.model.Contato;
import coutinhodeveloper.com.coutinmessenger.model.Usuario;

/** Created by Guilherme Coutinho
 *  on 15/09/2018
 */

public class MainActivity extends AppCompatActivity {

    private DatabaseReference firebase;
    private FirebaseAuth firebaseAuth;
    private Toolbar toolbar;
    private String identificadorContato;

    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebase = ConfiguracaoFirebase.getFirebase();
        firebaseAuth = FirebaseAuth.getInstance();

        toolbar = findViewById(R.id.toolbar_principal);
        toolbar.setTitle("Coutin Messenger");
        setSupportActionBar(toolbar);

        slidingTabLayout = findViewById(R.id.stl_tabs);
        viewPager = findViewById(R.id.vp_pagina);

        // cfg tamanho da slidingtablayout e cores
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this,R.color.colorPrimaryDark));

        // cfg adapter
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        slidingTabLayout.setViewPager(viewPager);


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
            case R.id.item_adicionar :
                abrirCadastroContato();
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

    private void abrirCadastroContato(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        //cfg
        alertDialog.setTitle("Novo contato");
        alertDialog.setMessage("Email do usuário");
        alertDialog.setCancelable(false);

        // cfg field of text
        final EditText editText = new EditText(this);
        alertDialog.setView(editText);

        // cfg buttons
        alertDialog.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String emailContato = editText.getText().toString();
                // validate
                if (emailContato.isEmpty()){
                    Toast.makeText(MainActivity.this,"Preencha o email",Toast.LENGTH_LONG).show();

                }else {

                    // verificação se o user está no banco de dados
                    identificadorContato = Base64Custom.converterBase64(emailContato);
                    // inst firebase
                    firebase = ConfiguracaoFirebase.getFirebase();
                    firebase = firebase.child("usuarios").child(identificadorContato);
                    // fazer uma unica consulta ao Firebase
                    firebase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            //verifica se dado foi retornado
                            if (dataSnapshot.getValue() != null){
                                // recuperando dados do contato a ser adicionado
                                Usuario usuarioContato = new Usuario();
                                usuarioContato = dataSnapshot.getValue(Usuario.class);

                                // recuperando dados do usuario logado
                                Preferencias preferencias = new Preferencias(MainActivity.this);
                                String identificadorUsuarioLogado = preferencias.getIdentificador();
                                Contato contato = new Contato();
                                contato.setIdentificadorUsuario(identificadorContato);
                                assert usuarioContato != null;
                                contato.setEmail(usuarioContato.getEmail());
                                contato.setNome(usuarioContato.getNome());

                                // salvando dados no firebase
                                firebase = ConfiguracaoFirebase.getFirebase()
                                .child("contatos")
                                .child(identificadorUsuarioLogado)
                                .child(identificadorContato);
                                firebase.setValue(contato);

                            }else {
                                Toast.makeText(MainActivity.this,"Usuário não possui cadastro", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

            }
        });

        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.create();
        alertDialog.show();

    }
}
