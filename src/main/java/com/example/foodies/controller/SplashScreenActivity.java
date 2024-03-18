package com.example.foodies.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;


import com.example.foodies.R;

import java.util.Locale;

public class SplashScreenActivity extends AppCompatActivity {
    TextToSpeech t1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status)
            {
                if(status !=TextToSpeech.ERROR)
                {
                    t1.setLanguage(Locale.UK);
                    t1.speak("welcome to foodie", TextToSpeech.QUEUE_FLUSH, null);

                }


            }
        });
    }
    public  void goto_UserSelectionScreen(View view)
    {
        Intent intent=new Intent(this,UserSelectionActivity.class);
        startActivity(intent);

    }
}