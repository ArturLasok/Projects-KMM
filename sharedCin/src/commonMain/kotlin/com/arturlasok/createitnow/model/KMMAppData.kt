package com.arturlasok.createitnow.model

import kotlinx.serialization.Serializable

@Serializable
data class KMMAppData(

val mAppId: Int? = null,
val mAppNamePL: String = "",
val mAppNameEng: String = "",
val mAppPlatformId: Int? = null,
//1 - and ,2 - ios, 3 - web, 4 - KMM and, ios, web
val mAppStoreLinkAndroid: String = "",
val mAppStoreLinkIOS: String ="",
val mAppStoreLinkWeb: String ="",
val mAppDescPL: String = "",
val mAppDescEng: String = "",
val mTechStack: String = ""

)


