package com.emplk.sharedflow_demo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.emplk.sharedflow_demo.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val hasButtonBeenClickedMutableSharedFlow = MutableSharedFlow<Unit>(extraBufferCapacity = 1)

    val viewEventLiveData: LiveData<Event<MainViewEvent>> = liveData {
        hasButtonBeenClickedMutableSharedFlow.collect {
            emit(Event(MainViewEvent.DisplayText))
        }
    }

    fun onButtonClicked() {
        hasButtonBeenClickedMutableSharedFlow.tryEmit(Unit)
    }
}