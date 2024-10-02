package com.example.androidtutorial

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class mainViewModel: ViewModel() {


    val numbersFlow: Flow<Int> = flow {
        repeat(60) { it ->
            emit(it+1) //Emits the result of the request to the flow
            delay(1000) //Suspends the coroutine for some time
        }
    }



}