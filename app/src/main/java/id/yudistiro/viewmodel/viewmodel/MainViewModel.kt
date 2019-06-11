package id.yudistiro.viewmodel.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import id.yudistiro.viewmodel.data.Word
import id.yudistiro.viewmodel.data.WordRepository
import id.yudistiro.viewmodel.data.WordRoomDatabase


class MainViewModel(application: Application) : AndroidViewModel(application){
    private val wordRepository : WordRepository
    val allword : LiveData<List<Word>>


    init {
        val wordDao = WordRoomDatabase.getDatabase(application,viewModelScope).wordDAO()
        wordRepository = WordRepository(wordDao)
        allword = wordRepository.allWords

    }

    fun insert(word: Word) = viewModelScope.launch {
        wordRepository.insert(word)
    }
}