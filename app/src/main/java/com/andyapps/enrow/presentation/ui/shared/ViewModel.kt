package com.andyapps.enrow.presentation.ui.shared

import androidx.lifecycle.viewModelScope
import com.andyapps.enrow.presentation.ui.feature.navigation.NavigationEvent
import com.andyapps.enrow.presentation.ui.feature.toast.ToastEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import androidx.lifecycle.ViewModel as AndroidViewModel

open class ViewModel : AndroidViewModel() {
    private val _navigationChannel = Channel<NavigationEvent>()
    val navigationFlow = _navigationChannel.receiveAsFlow()

    private val _toastChannel = Channel<ToastEvent>()
    val toastFlow = _toastChannel.receiveAsFlow()

    fun launchIn(
        context: CoroutineContext = Dispatchers.IO,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch(context) {
            block()
        }
    }

    fun navigate(event: NavigationEvent) {
        launchIn {
            navigateInScope(event)
        }
    }

    suspend fun navigateInScope(event: NavigationEvent) {
        _navigationChannel.send(event)
    }

    fun showToast(event: ToastEvent) {
        launchIn {
            showToastInScope(event)
        }
    }

    suspend fun showToastInScope(event: ToastEvent) {
        _toastChannel.send(event)
    }
}