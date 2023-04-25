package com.netacom.netalosdkandroid

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.json.JSONObject

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("onNewToken::", token)
    }

    @SuppressLint("LongLogTag")
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.e("FirebaseMessagingService::", message.data.toString())
        val jsonPayload = JSONObject(message.data.toString()).getJSONObject("Data").getString("Payload")
        val jsonObject = JSONObject(jsonPayload)
        Log.e("jsonPayload::", jsonPayload)
        Log.e("jsonObject::", jsonObject.toString())
        if (jsonObject.has("call")) {
            //Send notification call
            //NetAloSDK.onFirebaseReceivedCall(applicationContext, jsonPayload)
        } else {
            //Send notification message
           // NetAloSDK.onFirebaseReceivedChat(applicationContext, jsonPayload)
        }
        //Init logic parse message, call, secret chat v.v....
        //NetAloSDK.initFirebase(applicationContext, message)
    }
}