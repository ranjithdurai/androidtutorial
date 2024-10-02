package com.example.androidtutorial

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // This method is called when a new token is generated
        Log.d(TAG, "Refreshed token: $token")
        // Send token to your server or use as needed
        sendTokenToServer(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Handle FCM messages here
        Log.d(TAG, "From: " + remoteMessage.from)
        // Check if message contains a notification payload
        if (remoteMessage.notification != null) {
            Log.d(
                TAG, "Message Notification Body: " + remoteMessage.notification!!
                    .body
            )
        }
    }

    private fun sendTokenToServer(token: String) {
        // Implement this method to send token to your server or store it
    }

    companion object {
        private const val TAG = "FCMService"
    }
}
