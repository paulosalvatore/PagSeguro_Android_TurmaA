package br.com.paulosalvatore.pagseguroandroidturmaa.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.com.paulosalvatore.pagseguroandroidturmaa.entities.Word
import br.com.paulosalvatore.pagseguroandroidturmaa.repository.WordRepository

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = WordRepository(application)

    val allWords = repository.allWords

    fun insert(word: Word) {
        repository.insert(word)
    }
}
