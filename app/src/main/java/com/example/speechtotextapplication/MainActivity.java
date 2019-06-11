package com.example.speechtotextapplication;

import android.content.Intent;
import android.speech.RecognitionService;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView voiceInput, speakBtn;
    final int request_code = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        voiceInput = findViewById(R.id.tv);
        speakBtn = findViewById(R.id.btnspeak);
        speakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calling inbuilt speak recognizer
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                //to invoke lahguage support
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                //give local support to speech recognizer
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak something!!");
                startActivityForResult(intent, request_code);
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case request_code:
                if(resultCode==RESULT_OK && null!=data)
                {
                    ArrayList<String> stringArrayList=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    voiceInput.setText(stringArrayList.get(0));
                }
                break;
        }
    }
}
