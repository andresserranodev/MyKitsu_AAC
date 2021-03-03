package com.puzzlebench.kitsu_aac.repository

import java.lang.Exception

sealed class FetchingState {
    object Success : FetchingState()
    class Error(val error: Exception) : FetchingState()
}