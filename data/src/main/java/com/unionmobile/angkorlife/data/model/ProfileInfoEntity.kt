package com.unionmobile.angkorlife.data.model

import com.unionmobile.angkorlife.domain.model.ProfileInfo

data class ProfileInfoEntity(
    val fileArea: Int = 0,
    val displayOrder: Int = 0,
    val profileUrl: String = "",
    val mimeType: String = ""
)

fun ProfileInfoEntity.toModel() =
    ProfileInfo(
        fileArea = fileArea,
        displayOrder = displayOrder,
        profileUrl = profileUrl,
        mimeType = mimeType
    )