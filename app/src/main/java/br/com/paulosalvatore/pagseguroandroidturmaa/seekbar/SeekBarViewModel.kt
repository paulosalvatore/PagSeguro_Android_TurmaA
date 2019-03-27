package br.com.paulosalvatore.pagseguroandroidturmaa.seekbar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SeekBarViewModel : ViewModel() {
    val seekBarValue = MutableLiveData<Int>()
}
