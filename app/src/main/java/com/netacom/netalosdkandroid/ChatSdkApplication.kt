/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */

package com.netacom.netalosdkandroid

import android.app.Application
import android.content.Context
import androidx.work.Configuration
import com.asia.sdkcore.entity.ui.theme.NeTheme
import com.asia.sdkcore.sdk.AccountKey
import com.asia.sdkcore.sdk.AppID
import com.asia.sdkcore.sdk.AppKey
import com.asia.sdkcore.sdk.SdkConfig
import com.asia.sdkui.ui.sdk.NetAloSDK
import com.asia.sdkui.ui.sdk.NetAloSdkCore

import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm
import javax.inject.Inject

/**Created by vantoan on 23,July,2020
Company: Netacom.
Email: huynhvantoan.itc@gmail.com
 */

@HiltAndroidApp
class ChatSdkApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var netAloSdkCore: NetAloSdkCore

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(netAloSdkCore.workerFactory)
            .build()

    private var neTheme = NeTheme(
        mainColor = "#f5783f",
        subColorLight = "#F9D9C9",
        subColorDark = "#683A00",
        toolbarDrawable = "#f5783f",
    )

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Realm.init(this)
    }

    override fun onCreate() {
        super.onCreate()
        NetAloSDK.initNetAloSDK(
            context = this,
            netAloSdkCore = netAloSdkCore,
            sdkConfig = SdkConfig(
                appId = 19,
                appKey = "E736ED992DADF",
                accountKey = "E736ED992DADF",
                isSyncContact = false,
                hidePhone = false,
                hideCreateGroup = false,
                hideAddInfoInChat = false,
                hideInfoInChat = false,
                hideCallInChat = false,
                classMainActivity = MainActivity::class.java.name
            ),
            neTheme = neTheme
        )
    }
}
