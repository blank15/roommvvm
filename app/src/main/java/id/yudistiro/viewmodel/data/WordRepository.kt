package id.yudistiro.viewmodel.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class WordRepository(private val wordDao: WordDao){
    var allWords: LiveData<List<Word>> = wordDao.getAllWords()

    @WorkerThread
    suspend fun insert(word:Word){
        wordDao.insert(word)
    }
}