package coutinhodeveloper.com.coutinmessenger.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import coutinhodeveloper.com.coutinmessenger.R;
import coutinhodeveloper.com.coutinmessenger.activity.ChatActivity;
import coutinhodeveloper.com.coutinmessenger.adapter.ConversaAdapter;
import coutinhodeveloper.com.coutinmessenger.application.ConfiguracaoFirebase;
import coutinhodeveloper.com.coutinmessenger.helper.Base64Custom;
import coutinhodeveloper.com.coutinmessenger.helper.Preferencias;
import coutinhodeveloper.com.coutinmessenger.model.Conversa;


/** Created by Guilherme Coutinho
 *  on 01/10/2018
 */

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversasFragment extends Fragment {
    private ListView listView;
    private ArrayAdapter<Conversa> arrayAdapter;
    private ArrayList<Conversa> conversas;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerConversas;


    public ConversasFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_conversas, container, false);

        //montando listview e adapter
        conversas = new ArrayList<>();
        listView = view.findViewById(R.id.lv_conversas);
        arrayAdapter = new ConversaAdapter(getActivity(),conversas);
        listView.setAdapter(arrayAdapter);

        firebase = ConfiguracaoFirebase.getFirebase();
        //recuperando conversas do firebase
        Preferencias preferencias = new Preferencias(getActivity());
        String idUsuarioLogado = preferencias.getIdentificador();

        //instancia do fb
        firebase = ConfiguracaoFirebase.getFirebase()
                .child("conversas")
                .child(idUsuarioLogado);
        valueEventListenerConversas = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                conversas.clear();
                for (DataSnapshot dados : dataSnapshot.getChildren()){
                    Conversa conversa = dados.getValue(Conversa.class);
                    conversas.add(conversa);

                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        firebase.addValueEventListener(valueEventListenerConversas);

        // evento de click na lista de conversas
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //recuperar conversa para position
                Conversa conversa = conversas.get(position);
                //intent para chatActivity
                Intent intent = new Intent(getActivity(),ChatActivity.class);
                String email = Base64Custom.decodificarBase64(conversa.getIdUsuario());
                intent.putExtra("email", email);
                intent.putExtra("nome",conversa.getNome());
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerConversas);
    }

    @Override
    public void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerConversas);

    }

}
