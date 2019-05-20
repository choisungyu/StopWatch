package com.csg.stopwatch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.concurrent.timer

class ViewModel : ViewModel() {


//    private lateinit var time2: MutableLiveData<Int>
    //     public MutableLiveData<Integer> time = new MutableLiveData<>();
    val time: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    //    private var isRunning = false
    // 선언 하면서 객체 생성
    var isRunning = MutableLiveData<Boolean>()

    // init 에서 초기화
    init {
        time.value = 0
        isRunning.value = false
    }

    private var timerTask: Timer? = null

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
        isRunning.value = !isRunning.value!!
        if (isRunning.value!!) {
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