package com.unionmobile.angkorlife.data.model

import com.unionmobile.angkorlife.domain.model.ProfileInfo

data class ProfileInfoEntity(
    val profileUrl: String = "",
    val mimeType: String = ""
)

fun ProfileInfoEntity.toModel() =
    ProfileInfo(
        profileUrl = profileUrl,
        mimeType = mimeType
    )