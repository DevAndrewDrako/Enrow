package com.andyapps.enrow.domain.error

open class EnrowException(message: String) : Exception("Enrow domain exception. $message")