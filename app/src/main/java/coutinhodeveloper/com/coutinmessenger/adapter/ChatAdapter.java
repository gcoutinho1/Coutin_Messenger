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

/** Created by Guilherme Coutinho
 *  on 01/10/2018
 */

import coutinhodeveloper.com.coutinmessenger.R;
import coutinhodeveloper.com.coutinmessenger.helper.Preferencias;
import coutinhodeveloper.com.coutinmessenger.model.Mensagem;

public class ChatAdapter extends ArrayAdapter<Mensagem> {

    private Context context;
    private ArrayList<Mensagem> mensagens;
    public ChatAdapter(@NonNull Context c, @NonNull ArrayList<Mensagem> objects) {
        super(c, 0, objects);
        this.context = c;
        this.mensagens = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view=null;


        //verificando se a lista está vazia
        if (mensagens != null){
            //recuperando mensagens
            Mensagem mensagem = mensagens.get(position);

            //recuperar usuario logado
            Preferencias preferencias = new Preferencias(context);
            String idUsuarioLogado = preferencias.getIdentificador();
            //inicializando objeto para montagem do layout
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            // montar a view atraves do xml
            if (idUsuarioLogado.equals(mensagem.getIdUsuario())){
                view = layoutInflater.inflate(R.layout.item_mensagem_direita,parent,false);

            }else {
                view = layoutInflater.inflate(R.layout.item_mensagem_esquerda,parent,false);
            }


            //recuperar dados do firebase para exibicao
            TextView textView = view.findViewById(R.id.tv_mensagem);
            textView.setText(mensagem.getIdMensagem());



        }

        return view;
    }
}
