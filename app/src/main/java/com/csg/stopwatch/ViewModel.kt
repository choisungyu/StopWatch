package com.csg.stopwatch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.concurrent.timer

class ViewModel : ViewModel() {

//    private lateinit var time2: MutableLiveData<Int>
    val time: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    init {
        time.value = 0
    }

    private var timerTask: Timer? = null
    private var isRunning = false

    private fun pause() {
        // null 이 아니라면 cancel
        timerTask?.cancel()
    }

    private fun start() {
        timerTask = timer(period = 10) {
            // Background 에서 LiveData값 갱신할 때는 postValue 사용
            time.postValue(time.value?.plus(1))

        }
    }

    fun onStartButtonClicked() {
        isRunning = !isRunning
        if (isRunning) {
            start()
        } else {
            pause()
        }
    }

    override fun onCleared() {
        pause()
        timerTask = null
        super.onCleared()
    }
}