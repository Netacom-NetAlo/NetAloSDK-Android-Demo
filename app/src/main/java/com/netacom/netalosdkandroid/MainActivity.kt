package com.netacom.netalosdkandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.asia.sdkbase.logger.Logger
import com.asia.sdkcore.model.sdk.SDKTheme
import com.asia.sdkcore.model.ui.user.NeUser
import com.asia.sdkui.sdk.ChatSDK
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

@ExperimentalCoroutinesApi
@FlowPreview
class MainActivity : AppCompatActivity() {
    private val userDemo1 = NeUser(
        id = 2814749772832149,
        token = "0641123a602b34854bdda80fbd3aebf3a69eqdXN",
        username = "Toan 8888"
    )

    private val userDemo2 = NeUser(
        id = 2814749772693227,
        token = "0342c809e0966a92491782d19c1276ab1eeeU9ee",
        username = "Toan 9999"
    )
    private var isUserDemo1 = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        resultListener()
        findViewById<AppCompatButton>(R.id.btnSdkThemeGreen).setOnClickListener {
            ChatSDK.initTheme(
                SDKTheme(
                    mainColor = "#00B14F",
                    toolbarColor = "#00B14F"
                )
            )
        }
        findViewById<AppCompatButton>(R.id.btnSdkThemeOrange).setOnClickListener {
            ChatSDK.initTheme(
                SDKTheme(
                    mainColor = "#f5783f",
                    toolbarColor = "#f5783f"
                )
            )
        }
        findViewById<AppCompatButton>(R.id.btnSdk).setOnClickListener {
            isUserDemo1 = true
            ChatSDK.setUserSDK(userDemo1)
        }
        findViewById<AppCompatButton>(R.id.btnSdkOpenChatChange).setOnClickListener {
            isUserDemo1 = false
            ChatSDK.setUserSDK(userDemo2)
        }
        findViewById<AppCompatButton>(R.id.btnSdkOpen).setOnClickListener {
            ChatSDK.openListConversationSDK()
            MainScope().launch {
                delay(5000)
                // NetAloSDK.getListContactLocal()
            }
        }
        findViewById<AppCompatButton>(R.id.btnSdkOpenChat).setOnClickListener {
            ChatSDK.openChatSDK(neUser = if (isUserDemo1) userDemo2 else userDemo1)
            /* MainScope().launch {
                 delay(5000)
                 NetAloSDK.netAloEvent?.send(SdkCustomChatSend(hideCall = false))
             }*/
        }

        findViewById<AppCompatButton>(R.id.btnBlockUser).setOnClickListener {
            /*ChatSDK.blockUser(userId = user9.id, isBlock = true, callbackResult = object : CallbackResult<Boolean> {
                override fun callBackSuccess(result: Boolean) {
                    Logger.e("btnBlockUser:callBackSuccess=")
                }

                override fun callBackError(error: String?) {
                    Logger.e("btnBlockUser:callBackError=")
                }

            })*/
        }

        findViewById<AppCompatButton>(R.id.btnCustomUser).setOnClickListener {
            val userId = findViewById<AppCompatEditText>(R.id.editId).text.toString().toLongOrNull()
            val token = findViewById<AppCompatEditText>(R.id.editToken).text.toString()
            if (userId == null || token.isEmpty()) {
                Snackbar.make(findViewById(R.id.view), "Mời nhập ID và token để set User custom!", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            ChatSDK.setUserSDK(
                neUser = NeUser(
                    id = userId,
                    token = token,
                    username = findViewById<AppCompatEditText>(R.id.editName).text.toString(),
                    avatar = findViewById<AppCompatEditText>(R.id.editAvatar).text.toString(),
                    email = findViewById<AppCompatEditText>(R.id.editEmail).text.toString(),
                )
            )
        }

        findViewById<AppCompatButton>(R.id.btnSdkLogOut).setOnClickListener {
            //NetAloSDK.netAloEvent?.send(LocalFileModel(filePath = ""))
            val map: MutableMap<String, String> = mutableMapOf()
            map["test"] = "data"
            //ChatSDK.eventFireBase(map)
        }

        findViewById<AppCompatButton>(R.id.btnSdkListContact).setOnClickListener {
            /*NetAloSDK.getListContactFromServer { listContact ->
                Logger.e("listContact=" + listContact.map { it })
            }*/
        }

        findViewById<AppCompatButton>(R.id.btnStartActivitySdk).setOnClickListener {
            // NetAloSDK.openGallery(context = this, maxSelections = 1, autoDismissOnMaxSelections = false, galleryType = GalleryType.GALLERY_ALL)
        }
    }

    private fun resultListener() {
        /* CoroutineScope(Dispatchers.Default).launch {
             NetAloSDK.netAloEvent?.receive<ArrayList<LocalFileModel>>()?.collect { listPhoto ->
                 Logger.e("SELECT_PHOTO_VIDEO==$listPhoto")
             }
         }

         CoroutineScope(Dispatchers.Default).launch {
             NetAloSDK.netAloEvent?.receive<LocalFileModel>()?.collect { document ->
                 Logger.e("SELECT_FILE==$document")
             }

         }

         CoroutineScope(Dispatchers.Default).launch {
             NetAloSDK.netAloEvent?.receive<Map<String, String>>()?.collect { notification ->
                 Logger.e("Notification:data==$notification")
             }
         }

         CoroutineScope(Dispatchers.Default).launch {
             NetAloSDK.netAloEvent?.receive<Int>()?.collect { errorEvent ->
                 Logger.e("Event:==$errorEvent")
                 when (errorEvent) {
                     ErrorCodeDefine.ERRORCODE_FAILED_VALUE -> {
                         Logger.e("Event:Socket error")
                     }
                     ErrorCodeDefine.ERRORCODE_EXPIRED_VALUE -> {
                         Logger.e("Event:Session expired")
                     }
                     ErrorCodeDefine.ERRORCODE_LOGIN_CONFLICT_VALUE -> {
                         Logger.e("Event:Login conflict")
                     }
                 }
             }
         }*/
    }
}