package coutinhodeveloper.com.coutinmessenger.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/** Created by Guilherme Coutinho
 *  on 16/09/2018
 */

public class Preferencias {

     private Context contexto;
    private SharedPreferences preferences;
    private final String NOME_ARQUIVO = "coutinpreferencias";
    private int MODE = 0;
    private SharedPreferences.Editor editor;
    private final String CHAVE_IDENTIFICADOR = "identificadorUsuario";
    private final String CHAVE_NOME = "nome";


    @SuppressLint("CommitPrefEdits")
    public Preferencias (Context contextoParametro){
        contexto = contextoParametro;
        preferences = contexto.getSharedPreferences(NOME_ARQUIVO, MODE);
        editor = preferences.edit();
    }

    public  void salvarDados( String identificador, String nome ){
        editor.putString(CHAVE_IDENTIFICADOR, identificador);
        editor.putString(CHAVE_NOME, nome);
        editor.apply();
    }

    public String getIdentificador(){
        return preferences.getString(CHAVE_IDENTIFICADOR,null);

    }

    public String getNome(){
        return preferences.getString(CHAVE_NOME,null);

    }

}
