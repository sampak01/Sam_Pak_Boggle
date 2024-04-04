package com.example.sam_pak_boggle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BlankViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val currentScore: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

}