/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */

package com.netacom.netalosdkandroid

import android.app.Application
import com.asia.sdkcore.model.sdk.*
import com.asia.sdkui.sdk.ChatSDK

/**Created by vantoan on 23,July,2020
Company: Netacom.
Email: huynhvantoan.itc@gmail.com
 */

class ChatSdkApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        ChatSDK.initSDK(
            context = this,
            sdkInit = SDKInit(
                key = SDKKey(
                    appId = 1,
                    appKey = "appkey",
                    accountKey = "adminkey"
                ),
                theme = SDKTheme(
                    mainColor = "#f5783f",
                    toolbarColor = "#f5783f"
                ),
                config = SDKConfig(
                    isSyncContact = false,
                    hidePhone = false,
                    hideCreateGroup = false,
                    hideAddInfoInChat = false,
                    hideInfoInChat = false,
                    hideCallInChat = false,
                    classMainActivity = MainActivity::class.java.name
                ),
                setting = SDKSetting(

                )
            )
        )
    }
}
