package com.mwss.mytravelexpenses.model

enum class ClaimStatusEnum(val status: String) {
    OPEN("open"),
    SUBMITTED("submitted"),
    APPROVED("approved"),
    DECLINED("declined"),
    PAID("paid")
}