package com.unionmobile.angkorlife.feature.detail.model

import com.unionmobile.angkorlife.domain.model.ProfileInfo

data class ProfileInfoModel(
    val profileUrl: String = "",
    val mimeType: String = ""
)

fun ProfileInfo.toPresentation() =
    ProfileInfoModel(
        profileUrl = profileUrl,
        mimeType = mimeType
    )