package com.ludimosdemo.home.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ludimosdemo.models.BallTrackResponse
import com.ludimosdemo.repositories.BallTrackRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LudimosViewModel @Inject constructor(private val repository: BallTrackRepository): ViewModel() {

    private val _response: MutableLiveData<BallTrackResponse> = MutableLiveData()
    val response: LiveData<BallTrackResponse>
    get() = _response
    fun getBallTrackResponse(){
        viewModelScope.launch {
            val res = repository.getBallTrackResponse()
            _response.value = res
        }
    }

}