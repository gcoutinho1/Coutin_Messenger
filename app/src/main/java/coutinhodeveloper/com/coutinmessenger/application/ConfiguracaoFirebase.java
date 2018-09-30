package coutinhodeveloper.com.coutinmessenger.application;


import android.app.Application;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/** Created by Guilherme Coutinho
 *  on 17/09/2018
 */

public final class ConfiguracaoFirebase  {

    private static DatabaseReference firebase;
    private static final String URL_FIREBASE = "https://coutinmessenger.firebaseio.com/";

    public static DatabaseReference getFirebase(){

        if (firebase==null){
            firebase = FirebaseDatabase.getInstance().getReference();

         }

        return firebase;
    }

}
