package com.unionmobile.angkorlife.feature.detail.model

import com.unionmobile.angkorlife.domain.model.MimeType
import com.unionmobile.angkorlife.domain.model.ProfileInfo

data class ProfileInfoModel(
    val profileUrl: String = "",
    val mimeType: MimeType = MimeType.UNKNOWN
)

fun ProfileInfo.toPresentation() =
    ProfileInfoModel(
        profileUrl = profileUrl,
        mimeType = mimeType
    )