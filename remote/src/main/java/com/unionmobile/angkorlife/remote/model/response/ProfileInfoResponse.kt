package com.unionmobile.angkorlife.remote.model.response

import com.unionmobile.angkorlife.data.model.ProfileInfoEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileInfoResponse(
    @SerialName("fileArea") val fileArea: Int = 0,
    @SerialName("displayOrder") val displayOrder: Int = 0,
    @SerialName("profileUrl") val profileUrl: String = "",
    @SerialName("mimeType") val mimeType: String = ""
)

fun ProfileInfoResponse.toEntity() =
    ProfileInfoEntity(
        profileUrl = profileUrl,
        mimeType = mimeType
    )