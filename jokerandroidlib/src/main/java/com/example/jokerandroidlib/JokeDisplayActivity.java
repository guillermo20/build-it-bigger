package com.example.jokerandroidlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    private final String TAG = JokeDisplayActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
        String joke = getIntent().getStringExtra("joke");
        Log.i(TAG,joke);
        TextView  textView = (TextView) findViewById(R.id.jokedisplay_textview);
        textView.setText(joke);
    }



}
