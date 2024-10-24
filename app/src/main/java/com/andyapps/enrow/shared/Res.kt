package com.andyapps.enrow.shared

sealed interface Res<DATA, ERROR> {
    data class Success<DATA, ERROR>(val data: DATA) : Res<DATA, ERROR>
    data class Error<DATA, ERROR>(val error: ERROR) : Res<DATA, ERROR>
}