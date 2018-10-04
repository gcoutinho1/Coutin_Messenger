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
import coutinhodeveloper.com.coutinmessenger.model.Contato;

/** Created by Guilherme Coutinho
 *  on 01/10/2018
 */

public class ContatoAdapter extends ArrayAdapter<Contato> {

     private Context context;
     private ArrayList<Contato> contatos;


     public ContatoAdapter(@NonNull Context c, @NonNull ArrayList<Contato> objects) {
        super(c, 0, objects);
        this.context = c;
        this.contatos = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

         View view = null;
         //verificando se a lista não está vazia
        if (contatos != null){
            //inicializa objeto para montagem do layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            //monta a view através do xml
            assert inflater != null;
            view = inflater.inflate(R.layout.lista_contatos,parent,false);

            //recuperando elements para view
            TextView textView = view.findViewById(R.id.tv_nome);

            Contato contato = contatos.get(position);
            textView.setText(contato.getNome());

        }


         return view;
    }
}
