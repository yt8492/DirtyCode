package com.yt8492.dirtycode.data.model

import java.util.*

data class Group(
    val id: Int,
    val name: String,
    val urlName: String,
    val isPrivate: Boolean,
    val createdAt: Date,
    val updatedAt: Date
)