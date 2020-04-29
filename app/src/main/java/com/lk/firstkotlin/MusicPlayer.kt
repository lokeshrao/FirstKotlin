package com.lk.firstkotlin

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_musicplayer.*

class  MusicPlayer : AppCompatActivity() {

    private lateinit var musicplayer : MediaPlayer
    private var totalTime : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_musicplayer)

//        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
//        val ringtone = RingtoneManager.getRingtone(this,uri)
//        ringtone.play()


        musicplayer = MediaPlayer.create(this,R.raw.music)
        musicplayer.isLooping = true
        musicplayer.setVolume(0.5f,0.5f)
        totalTime = musicplayer.duration

        volumeBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser){
                        var volumeNum = progress / 100.0f
                        musicplayer.setVolume(volumeNum,volumeNum)

                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }

            }
        )

        positionBar.max = totalTime
        positionBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {

                    if (fromUser){

                        musicplayer.seekTo(progress)

                    }

                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }

            }
        )

        Thread(Runnable {

            while (musicplayer != null){

                try {

                    var msg = Message()
                    msg.what = musicplayer.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                } catch ( e : InterruptedException){
                }
            }
        }).start()
    }

    @SuppressLint("HandlerLeak")
    var handler = object : Handler(){
        override fun handleMessage(msg: Message) {
            var currentPosition = msg.what

            //update postionBar
            positionBar.progress = currentPosition

            //update Labels
            var elapsedTime = createTimeLabel(currentPosition)
            elapsedTimeLabel.text = elapsedTime

            var remainingTime = createTimeLabel(totalTime - currentPosition)
            remainingTimeLabel.text = "-$remainingTime"
        }
    }

   fun createTimeLabel(time : Int) : String {
       var timeLabel = ""
       var min = time / 1000 / 60
       var sec = time / 1000 % 60

       timeLabel = "$min:"
       if (sec < 10) timeLabel += "0"
       timeLabel += sec

       return timeLabel

    }

    fun playBtnClick(v : View){

        if (musicplayer.isPlaying){

            musicplayer.pause()
            playBtn.setBackgroundResource(R.drawable.play)

        } else {

            musicplayer.start()
            playBtn.setBackgroundResource(R.drawable.stop)

        }
    }
}