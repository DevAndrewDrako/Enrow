package com.andyapps.enrow.presentation.ui.feature.toast

sealed interface ToastEvent {
    data class Show(val message: String) : ToastEvent
}