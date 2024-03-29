//package com.example.noteapp.worker
//
//import android.Manifest
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.app.PendingIntent
//import android.content.Context
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.os.Build
//import android.util.Log
//import androidx.core.app.ActivityCompat
//import androidx.core.app.NotificationCompat
//import androidx.core.app.NotificationManagerCompat
//import androidx.work.*
//import com.example.noteapp.R
//import com.noteapp.ui.addNote.AddNoteFragment
//
//class ReminderWorker(
//    context: Context,
//    workerParams: WorkerParameters
//) : Worker(context, workerParams) {
//
//    companion object {
//        const val CHANNEL_ID = "reminder_id"
//      const  val notificationId = 1
//
//
//    }
//
//    override fun doWork(): Result {
//        Log.d("doWork","doWork: Success function called")
//        showNotification()
//        return Result.success()
//    }
//
//
//    fun showNotification(){
//        val intent = Intent(applicationContext, AddNoteFragment::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
//
//        val pendingIntent: PendingIntent = PendingIntent
//            .getActivity(applicationContext, 0, intent, 0)
//
//
//        val builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_baseline_note_alt_24)
//            .setContentTitle("Reminder")
//            .setContentText("It's time add some notes ")
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setContentIntent(pendingIntent)
//            .setAutoCancel(true)
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channelName = "worker_channel"
//            val channelDescription = "channel_description"
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val channel = NotificationChannel(CHANNEL_ID, channelName, importance).apply {
//                description = channelDescription
//            }
//            val notificationManager=applicationContext.getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//        with(NotificationManagerCompat.from(applicationContext)) {
//            if (ActivityCompat.checkSelfPermission(
//                    this,
//                    Manifest.permission.POST_NOTIFICATIONS
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return
//            }
//            notify(notificationId, builder.build())
//        }
//    }
//
//
//    }
//
//
