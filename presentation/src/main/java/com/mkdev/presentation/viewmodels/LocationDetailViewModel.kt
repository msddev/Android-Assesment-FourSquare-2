package com.mkdev.presentation.viewmodels

import com.mkdev.presentation.utils.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider
) : BaseViewModel(contextProvider) {

    override val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, exception ->

        }
}