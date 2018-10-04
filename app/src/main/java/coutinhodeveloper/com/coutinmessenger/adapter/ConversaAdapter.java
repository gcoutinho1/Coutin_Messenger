package coutinhodeveloper.com.coutinmessenger.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import coutinhodeveloper.com.coutinmessenger.R;
import coutinhodeveloper.com.coutinmessenger.model.Conversa;


/** Created by Guilherme Coutinho
 *  on 02/10/2018
 */



public class ConversaAdapter extends ArrayAdapter<Conversa> {

    private ArrayList<Conversa> conversas;
    private Context context;
    private Conversa conversa;

    public ConversaAdapter(@NonNull Context c, @NonNull ArrayList<Conversa> objects) {
        super(c, 0, objects);
        this.conversas = objects;
        this.context = c;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;

        //verificando se a lista está preenchida
        if (conversas != null){
            //inicializa objetos para montagem do layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // monta a view apartir do xml
            assert inflater != null;
            view = inflater.inflate(R.layout.lista_conversas,parent,false);

            // recuperando elementos da tela
            TextView nome = view.findViewById(R.id.text_nome);
            TextView ultimaMensagem = view.findViewById(R.id.text_ultima_conversa);

            // setando valores componentes de tela
            conversa = conversas.get(position);
            nome.setText(conversa.getNome());
            ultimaMensagem.setText(conversa.getMensagem());
        }

        return view;

    }
}
