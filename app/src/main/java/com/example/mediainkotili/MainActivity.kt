package com.example.mediainkotili

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.SeekBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Objects

class MainActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? =null
    private lateinit var seekbar: SeekBar
    private lateinit var runnable: Runnable
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        seekbar = findViewById(R.id.sbchronix)
        handler= Handler(Looper.getMainLooper())
        val play:FloatingActionButton =findViewById(R.id.fabplay)
        play.setOnClickListener(){
            if (mediaPlayer==null){
                mediaPlayer=MediaPlayer.create(this,R.raw.chronixx_ghetto_paradise_4_chronology_link_in_bio_mp3_32190)
                seekbar()
            }
            mediaPlayer?.start()
        }
        val stop:FloatingActionButton=findViewById(R.id.fabStop)
        stop.setOnClickListener(){
            mediaPlayer?.stop()
            mediaPlayer?.reset()
            mediaPlayer?.release()
            handler.removeCallbacks(runnable)
            seekbar.progress=0
        }
        val pause:FloatingActionButton=findViewById(R.id.fabpause)
        pause.setOnClickListener(){
            mediaPlayer?.pause()


        }
    }
    private fun seekbar(){
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            if (fromUser)mediaPlayer?.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        seekbar.max=mediaPlayer!!.duration
        runnable = Runnable {
          seekbar.progress= mediaPlayer!!.currentPosition
            handler.postDelayed(runnable,1000)
        }
        handler.postDelayed(runnable,1000)
    }
}