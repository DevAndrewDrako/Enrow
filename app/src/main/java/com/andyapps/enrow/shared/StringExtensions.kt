package com.andyapps.enrow.shared

import java.util.UUID

fun String.toUUID() : UUID? {
    return try {
        UUID.fromString(this)
    }
    catch (ex: Exception) {
        return null
    }
}