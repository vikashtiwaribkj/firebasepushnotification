package com.hiappz.firebasepushnotificationlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class ActivityPushNotificationMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notification_main);

    }

    /**
     * @help initFirebaseSdk method takes credentials from google-service.json file you downloaded from firebase console
     * @param mobilesdk_app_id indicates application id your app registered in firebase console. It can be found in client/client_info/mobilesdk_app_id in google-service.json
     * @param api_key_current_key indicates api key or google api key. It can be found in client/api_key/current_key in google-service.json
     */
    private void initFirebaseSdk(String mobilesdk_app_id, String api_key_current_key, String firebase_url, String storage_bucket) {
        FirebaseOptions.Builder firebaseOptionsBuilder = new FirebaseOptions.Builder()
                .setApplicationId(mobilesdk_app_id)  //   "mobilesdk_app_id"          :   {YOUR_CLIENT}/client_info/mobilesdk_app_id
                .setApiKey(api_key_current_key)         //   "api_key" ->"current_key"   :   {YOUR_CLIENT}/api_key/current_key
                .setDatabaseUrl(firebase_url)    //   "firebase_url"              :   project_info/firebase_url
                .setStorageBucket(storage_bucket); //    "storage_bucket"           :   project_info/storage bucket

        FirebaseApp.initializeApp(this, firebaseOptionsBuilder.build());
    }
}
