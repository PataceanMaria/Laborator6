package com.example.services_homework;

import static android.media.AudioManager.STREAM_MUSIC;
import static android.media.ToneGenerator.TONE_DTMF_1;

import android.app.Service;
import android.content.Intent;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";
    ToneGenerator toner;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate: ");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String value=intent.getStringExtra("Value1");
        String period=intent.getStringExtra("Period1");
        String duration=intent.getStringExtra("Duration1");

        toner = new ToneGenerator(STREAM_MUSIC,Integer.parseInt(value));

        Log.i(TAG, "Continue: ");
        Log.i(TAG, "period: "+period);
        Log.i(TAG, "value: "+value);
        Log.i(TAG, "duration: "+duration);
        toner.startTone(TONE_DTMF_1);


        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                int nr=0;
                for(int i=0;i<Integer.parseInt(duration);i++){
                    nr++;
                    Log.i(TAG, "ring: "+nr);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(nr==Integer.parseInt(duration))
                    toner.stopTone();

            }
        };
        Thread service_task_in_own_thread=new Thread(runnable);
        service_task_in_own_thread.start();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "Destroy: ");
        toner.stopTone();
        super.onDestroy();
    }
}