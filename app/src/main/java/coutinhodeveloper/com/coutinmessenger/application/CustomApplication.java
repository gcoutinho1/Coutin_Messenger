package coutinhodeveloper.com.coutinmessenger.application;

import android.app.Application;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;


/** Created by Guilherme Coutinho
 *  on 17/09/2018
 */

public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Firebase.setAndroidContext(this);  old version
        if(!FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }
}
