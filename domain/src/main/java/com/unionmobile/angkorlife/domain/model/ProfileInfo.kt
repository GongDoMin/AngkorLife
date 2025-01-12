package com.unionmobile.angkorlife.domain.model

data class ProfileInfo(
    val profileUrl: String = "",
    val mimeType: MimeType = MimeType.UNKNOWN
)

enum class MimeType {
    IMAGE_GIF, IMAGE_JPG, UNKNOWN
}
