package com.example.aaaa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private TextView timeTV;
    private Button increaseBtn, decreaseBtn, resetBtn;
    private FloatingActionButton playPauseFAB;
    private long timeInMilliseconds=0;
    private CountDownTimer timer;
    private boolean isTimerRunning = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) //save data when rotating phone
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeTV=findViewById(R.id.tv_time);
        timeTV.setText("0");
        increaseBtn=findViewById(R.id.btn_increase);
        decreaseBtn=findViewById(R.id.btn_decrease);
        resetBtn=findViewById(R.id.btn_reset);
        playPauseFAB=findViewById(R.id.fab_play_pause);

        increaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeInMilliseconds+=10000;
                timeTV.setText(String.valueOf(timeInMilliseconds/1000));
            }
        });
        decreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timeInMilliseconds>0){
                timeInMilliseconds-=10000;}
                timeTV.setText(String.valueOf(timeInMilliseconds/1000));
            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timeInMilliseconds=0;
                timeTV.setText(String.valueOf(timeInMilliseconds/1000));

            }
        });
        playPauseFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "play/pause", Toast.LENGTH_SHORT).show();
                if(isTimerRunning){
                    pauseTime();
                    isTimerRunning=false;

                }
                else
                {
                    playTime();
                    isTimerRunning=true;
                }
            }
        });

    }
    private void playTime()
    {
        timer= new CountDownTimer(timeInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeInMilliseconds=millisUntilFinished;
                timeTV.setText(String.valueOf(timeInMilliseconds/1000));
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }
    private void pauseTime()
    {
        timer.cancel();
    }
}
