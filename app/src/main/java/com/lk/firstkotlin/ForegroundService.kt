package com.lk.firstkotlin

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat


class ForegroundService : Service() {
    private val CHANNEL_ID = "ForegroundService Kotlin"
   lateinit var notificationwq: Uri
    companion object {
        fun startService(context: Context, message: String) {
            val startIntent = Intent(context, ForegroundService::class.java)
            startIntent.putExtra("inputExtra", message)
            ContextCompat.startForegroundService(context, startIntent)
        }
        fun stopService(context: Context) {
            val stopIntent = Intent(context, ForegroundService::class.java)
            context.stopService(stopIntent)
        }
    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this,"onCreate",1000).show()
//         notificationwq = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
//        val r = RingtoneManager.getRingtone(applicationContext, notificationwq)
//        val mAudioManager:AudioManager = getSystemService(AUDIO_SERVICE) as AudioManager;
//        val max_volume_level = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM)
//
//        //1st way: Thread.sleep : Less efficient compared to 2nd
//        //1st way: Thread.sleep : Less efficient compared to 2nd
//        var number=2000
//        val mNotificationManager =
//            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//r.play()
//
//        try {
//            while (number>0) { //Or any Loops
//                //Do Something
//
//                // Check if the notification policy access has been granted for the app.
//
//                // Check if the notification policy access has been granted for the app.
//                if (!mNotificationManager.isNotificationPolicyAccessGranted) {
//                    val intent =
//                        Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                    startActivity(intent)
//                }
//                mAudioManager.setStreamVolume(
//                    AudioManager.STREAM_RING,
//                    max_volume_level,
//                    AudioManager.FLAG_SHOW_UI
//                );
//                Thread.sleep(400)
//                number--//Sample: Thread.sleep(1000); 1 second sleep
//            }
//        } catch (ex: InterruptedException) {
//            //SomeFishCatching
//        }
        //do heavy work on a background thread
        val input = intent?.getStringExtra("inputExtra")
        createNotificationChannel()
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0
        )

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Service Kotlin Example")
            .setContentText(input)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)
        //stopSelf();
        return START_NOT_STICKY
    }
    override fun onBind(intent: Intent): IBinder? {
        return null
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(CHANNEL_ID, "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(NotificationManager::class.java)
            manager!!.createNotificationChannel(serviceChannel)
        }
    }

    override fun onCreate() {
        super.onCreate()

        Toast.makeText(this,"onCreate",1000).show()
        val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val r = RingtoneManager.getRingtone(applicationContext, notification)
        r.play()
    }

}
//class MyService : Service() {
//    private val NOTIF_ID = 1
//    private val NOTIF_CHANNEL_ID = "Channel_Id"
//
//    override fun onBind(intent: Intent): IBinder? {
//        throw UnsupportedOperationException("Not yet implemented")
//    }
//
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//
//        // do your jobs here
//        startForeground()
//        return super.onStartCommand(intent, flags, startId)
//    }
//
//    private fun startForeground(): Int {
//        val notificationIntent = Intent(this, MainActivity::class.java)
//        val pendingIntent = PendingIntent.getActivity(
//            this, 0,
//            notificationIntent, 0
//        )
//        startForeground(
//            NOTIF_ID, NotificationCompat.Builder(
//                this,
//                NOTIF_CHANNEL_ID
//            ) // don't forget create a notification channel first
//                .setOngoing(true)
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setContentTitle(getString(R.string.app_name))
//                .setContentText("Service is running background")
//                .setContentIntent(pendingIntent)
//                .build()
//        )
//        return START_STICKY
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
////        var receiver: RestartService
////        var intentFilter: IntentFilter
////        receiver = RestartService()
////        intentFilter = IntentFilter("com.journaldev.broadcastreceiver.SOME_ACTION")
////        registerReceiver(receiver, intentFilter);
////        val intent = Intent("com.journaldev.broadcastreceiver.SOME_ACTION")
////        sendBroadcast(intent)
////        Toast.makeText(
////            applicationContext,
////            "stop",
////            Toast.LENGTH_LONG).show()
//        Log.e("destroy","dedtroy")
//    }
//
//    // Custom method to do a task
//
//
//    override fun onTaskRemoved(rootIntent: Intent?) {
//        val restartServiceIntent = Intent(applicationContext, this.javaClass)
//        restartServiceIntent.setPackage(packageName)
//        val restartServicePendingIntent = PendingIntent.getService(
//            applicationContext,
//            1,
//            restartServiceIntent,
//            PendingIntent.FLAG_ONE_SHOT
//        )
//        val alarmService =
//            applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        alarmService[AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 1000] =
//            restartServicePendingIntent
//        super.onTaskRemoved(rootIntent)
//    }
//
//
//}
//
