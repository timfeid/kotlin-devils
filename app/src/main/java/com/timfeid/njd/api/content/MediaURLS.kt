package com.timfeid.njd.api.content


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MediaURLS(
    @SerialName("FLASH_1200K_640X360")
    val fLASH1200K640X360: String = "",
    @SerialName("FLASH_1800K_960X540")
    val fLASH1800K960X540: String = "",
    @SerialName("FLASH_192K_320X180")
    val fLASH192K320X180: String = "",
    @SerialName("FLASH_450K_400X224")
    val fLASH450K400X224: String = "",
    @SerialName("HTTP_CLOUD_MOBILE")
    val hTTPCLOUDMOBILE: String = "",
    @SerialName("HTTP_CLOUD_TABLET")
    val hTTPCLOUDTABLET: String = "",
    @SerialName("HTTP_CLOUD_TABLET_60")
    val hTTPCLOUDTABLET60: String = "",
    @SerialName("HTTP_CLOUD_WIRED")
    val hTTPCLOUDWIRED: String = "",
    @SerialName("HTTP_CLOUD_WIRED_60")
    val hTTPCLOUDWIRED60: String = "",
    @SerialName("HTTP_CLOUD_WIRED_WEB")
    val hTTPCLOUDWIREDWEB: String = ""
)