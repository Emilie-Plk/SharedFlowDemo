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

    private val hasButtonBeenClickedMutableSharedFlow = MutableSharedFlow<Boolean>()

    val viewEventLiveData: LiveData<Event<MainViewEvent>> = liveData {
        hasButtonBeenClickedMutableSharedFlow.collect { hasButtonBeenClicked ->
            if (hasButtonBeenClicked) {
                emit(
                    Event(
                        MainViewEvent.DisplayText
                    )
                )
            }
        }
    }


    fun onButtonClicked() {
        /*   tryEmit (non-blocking) ne marche pas dans ce cas...
             sauf si on met le replay à 1*/
        hasButtonBeenClickedMutableSharedFlow.tryEmit(true)

        //    ça fonctionne très bien avec emit par contre, pas besoin de replay
      /*  viewModelScope.launch {
            hasButtonBeenClickedMutableSharedFlow.emit(true)
        }*/
    }
}