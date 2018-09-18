package coutinhodeveloper.com.coutinmessenger.application;

import android.app.Application;

import com.firebase.client.Firebase;

/** Created by Guilherme Coutinho
 *  on 17/09/2018
 */

public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
