package com.example.services_homework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText time ,value,duration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         time=findViewById(R.id.Perioada);
         value=findViewById(R.id.volum_sunet);
         duration=findViewById(R.id.durata_timp);
    }

    public void Ringtone(View view)
    {
        Intent intent_1=new Intent(getApplicationContext(),MyService.class);
        String p=time.getText().toString();
        String v=value.getText().toString();
        String d=duration.getText().toString();
        intent_1.putExtra("Value1",v);
        intent_1.putExtra("Period1",p);
        intent_1.putExtra("Duration1",d);
        startService(intent_1);




    }
    public void stopBtn (View view)
    {
        Intent intent_1=new Intent(getApplicationContext(),MyService.class);
        stopService(intent_1);
    }
}