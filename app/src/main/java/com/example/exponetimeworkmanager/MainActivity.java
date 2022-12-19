package com.example.exponetimeworkmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Constraints C= new Constraints.Builder()
                .setRequiresCharging(true)
                //.setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresDeviceIdle(true)
                //quand la machine est en veille
                .build();
        Data D=new Data.Builder()
                .putString("aa", "moncontenudata")
                .putInt("bb",45)
                .build();


//Préparation de la demande de travail
        WorkRequest WR = new OneTimeWorkRequest
                .Builder(MyWork.class)
                .setConstraints(C)
                .setInputData(D)
               // .setInitialDelay(20, TimeUnit.SECONDS)
                // pour un travail différé
                .build();

        //Demande d'exécution du travail
        WorkManager.getInstance(this).enqueue(WR);

    }
}