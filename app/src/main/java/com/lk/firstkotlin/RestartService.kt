package com.lk.firstkotlin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class RestartService : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.e("broad121212cast","dedtroy")
//        context.startService(Intent(context, MyService::class.java))        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
//        context.startService(Intent(context, MyService::class.java))

    }
}
