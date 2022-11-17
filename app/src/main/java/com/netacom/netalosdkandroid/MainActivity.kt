package com.netacom.netalosdkandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.snackbar.Snackbar
import com.asia.sdkbase.binding.clickDebounce
import com.asia.sdkbase.logger.Logger
import com.asia.sdkui.ui.sdk.NetAloSDK
import com.asia.sdkcore.config.EndPoint
import com.asia.sdkcore.define.ErrorCodeDefine
import com.asia.sdkcore.define.GalleryType
import com.asia.sdkcore.entity.ui.local.LocalFileModel
import com.asia.sdkcore.entity.ui.theme.NeTheme
import com.asia.sdkcore.entity.ui.user.NeUser
import com.asia.sdkcore.network.model.response.SettingResponse
import com.asia.sdkcore.sdk.SdkCustomChatSend
import com.asia.sdkcore.util.CallbackResult
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
@FlowPreview
class MainActivity : AppCompatActivity() {

    private val user8 = NeUser(
        id = 5348024558706782,
        token = "0409a3591c69028242dba72dfd719fa73927bnXO",
        username = "Toan 8888",
        // avatar = "Attachments/f91f5ef2-fa03-4d73-b549-60b6ca3c90a0_332CF1D4-8681-4EAF-9EC7-5BB42E8AF5EF.jpg",
        isAdmin = true
    )

    // private val user8 = NeUser(id = 3096224744870411, token = "8f3c7909ec8152ce0ae3355c0ff0a55968a98579", username = "Toan 0000", avatar = "Attachments/f91f5ef2-fa03-4d73-b549-60b6ca3c90a0_332CF1D4-8681-4EAF-9EC7-5BB42E8AF5EF.jpg", isAdmin = true)

    private val user9 = NeUser(
        id = 2814749772693227,
        token = "777011f136b8edb137e92694b671190c174d8d7a",
        username = "Toan 99999",
        // avatar = "Attachments/f91f5ef2-fa03-4d73-b549-60b6ca3c90a0_332CF1D4-8681-4EAF-9EC7-5BB42E8AF5EF.jpg",
        isAdmin = true
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        resultListener()
        findViewById<AppCompatButton>(R.id.btnSdkThemeGreen).clickDebounce {
            NetAloSDK.initTheme(
                NeTheme(
                    mainColor = "#00B14F",
                    subColorLight = "#D6F3E2",
                    subColorDark = "#683A00",
                    toolbarDrawable = "#00B14F"
                )
            )
            NetAloSDK.initSetting(
                settingResponse = SettingResponse(
                    apiEndpoint = EndPoint.URL_API,
                    cdnEndpoint = EndPoint.URL_CDN,
                    chatEndpoint = EndPoint.URL_SOCKET
                )
            )
        }
        findViewById<AppCompatButton>(R.id.btnSdkThemeOrange).clickDebounce {
            NetAloSDK.initTheme(
                NeTheme(
                    mainColor = "#f5783f",
                    subColorLight = "#4df5783f",
                    subColorDark = "#683A00",
                    toolbarDrawable = "#f5783f"
                )
            )
        }
        findViewById<AppCompatButton>(R.id.btnSdk).clickDebounce {
            NetAloSDK.setNetAloUser(user8)
        }
        findViewById<AppCompatButton>(R.id.btnSdkOpenChatChange).clickDebounce {
            NetAloSDK.setNetAloUser(user9)
        }
        findViewById<AppCompatButton>(R.id.btnSdkOpen).clickDebounce {
            NetAloSDK.openNetAloSDK(this)
            MainScope().launch {
                delay(5000)
                // NetAloSDK.getListContactLocal()
            }
        }
        findViewById<AppCompatButton>(R.id.btnSdkOpenChat).clickDebounce {
            NetAloSDK.openNetAloSDK(context = this, neUserChat = user9)
           /* MainScope().launch {
                delay(5000)
                NetAloSDK.netAloEvent?.send(SdkCustomChatSend(hideCall = false))
            }*/
        }

        findViewById<AppCompatButton>(R.id.btnBlockUser).clickDebounce {
            NetAloSDK.blockUser(userId = user9.id, isBlock = true, callbackResult = object : CallbackResult<Boolean> {
                override fun callBackSuccess(result: Boolean) {
                    Logger.e("btnBlockUser:callBackSuccess=")
                }

                override fun callBackError(error: String?) {
                    Logger.e("btnBlockUser:callBackError=")
                }

            })
        }

        findViewById<AppCompatButton>(R.id.btnCustomUser).clickDebounce {
            val userId = findViewById<AppCompatEditText>(R.id.editId).text.toString().toLongOrNull()
            val token = findViewById<AppCompatEditText>(R.id.editToken).text.toString()
            if (userId == null || token.isEmpty()) {
                Snackbar.make(findViewById(R.id.view), "Mời nhập ID và token để set User custom!", Snackbar.LENGTH_LONG).show()
                return@clickDebounce
            }
            NetAloSDK.setNetAloUser(
                neUser = NeUser(
                    id = userId,
                    token = token,
                    username = findViewById<AppCompatEditText>(R.id.editName).text.toString(),
                    avatar = findViewById<AppCompatEditText>(R.id.editAvatar).text.toString(),
                    email = findViewById<AppCompatEditText>(R.id.editEmail).text.toString(),
                )
            )
        }

        findViewById<AppCompatButton>(R.id.btnSdkLogOut).clickDebounce {
            //NetAloSDK.netAloEvent?.send(LocalFileModel(filePath = ""))
            val map: RemoteMessage = RemoteMessage(Bundle())
            NetAloSDK.initFirebase(this, map)
        }

        findViewById<AppCompatButton>(R.id.btnSdkListContact).clickDebounce {
            /*NetAloSDK.getListContactFromServer { listContact ->
                Logger.e("listContact=" + listContact.map { it })
            }*/
        }

        findViewById<AppCompatButton>(R.id.btnStartActivitySdk).clickDebounce {
            NetAloSDK.openGallery(context = this, maxSelections = 1, autoDismissOnMaxSelections = false, galleryType = GalleryType.GALLERY_ALL)
        }
    }

    private fun resultListener() {
        CoroutineScope(Dispatchers.Default).launch {
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
        }
    }
}