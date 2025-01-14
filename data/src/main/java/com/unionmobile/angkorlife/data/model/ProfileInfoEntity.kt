package com.unionmobile.angkorlife.data.model

import com.unionmobile.angkorlife.domain.model.MimeType
import com.unionmobile.angkorlife.domain.model.ProfileInfo

data class ProfileInfoEntity(
    val profileUrl: String = "",
    val mimeType: String = ""
)

fun ProfileInfoEntity.toModel() =
    ProfileInfo(
        profileUrl = profileUrl,
        mimeType = mimeType.toMineType()
    )

private fun String.toMineType() =
    when (this) {
        "image/gif", "image/jpg", "image/png" -> MimeType.IMAGE_JPG
        else -> MimeType.UNKNOWN
    }