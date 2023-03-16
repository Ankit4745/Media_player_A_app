package com.example.mediaplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    public void playme (View view){
        mediaPlayer.start();
    }

    public void stopme (View view){
        mediaPlayer.pause();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = mediaPlayer.create(MainActivity.this,R.raw.tu);


        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int myMaxVl = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int myCurrentVl = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar volumeRocker = findViewById(R.id.seekBar);
        volumeRocker.setMax(myMaxVl);
        volumeRocker.setProgress(myCurrentVl);


        volumeRocker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean a) {

                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        final SeekBar timelife = findViewById(R.id.seekBar2);
        timelife.setMax(mediaPlayer.getDuration());

        timelife.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean a) {
                mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timelife.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0, 1000);

    }
}