package com.boneo.proyectoboneoapp.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.boneo.proyectoboneoapp.R

import com.boneo.proyectoboneoapp.activities.base.MainActivity
import com.boneo.proyectoboneoapp.activities.comunicados.ComunicadosActivity
import com.boneo.proyectoboneoapp.activities.comunicados.ComunicadosDetailActivity
import com.boneo.proyectoboneoapp.model.ComunicadosRepository
import com.boneo.proyectoboneoapp.model.FireBaseTokenRepository
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


const val NOTIFICATION_TYPE_COMUNICADO = "comunicado"


class BoneoFireBaseService : FirebaseMessagingService() {

    /**
     * Called when message is received.
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        when(remoteMessage?.data?.get("notification_type")) {
            NOTIFICATION_TYPE_COMUNICADO -> { handleNotificacionComunicado(remoteMessage) }
            else -> {}
        }
    }

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    override fun onNewToken(token: String?) {
        FireBaseTokenRepository.createFireBaseToken(token!!) { _, _ -> }
    }

    private fun handleNotificacionComunicado(remoteMessage: RemoteMessage) {
        ComunicadosRepository.getComunicado(remoteMessage.data["id"]!!.toLong()) {
            destinatarioComunicado, _ ->
                val intent = Intent(this, ComunicadosActivity::class.java)
                intent.putExtra(ComunicadosActivity.detailKey, destinatarioComunicado)
                sendNotification(destinatarioComunicado?.comunicado?.asunto!!,
                        destinatarioComunicado.comunicado.emisor.nombre,
                        intent)
        }
    }

    private fun sendNotification(title: String, body: String, intent: Intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT)
        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_email_black_24dp)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }
}