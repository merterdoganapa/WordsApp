package com.mea.wordsapp

import androidx.annotation.WorkerThread
import com.mea.wordsapp.WordDao
import com.mea.wordsapp.Word
import kotlinx.coroutines.flow.Flow

/*
* Repository temel olarak veritabanı sorgulama işlemlerinin bir merkezden yapılmasını sağlayarak
* iş katmanına taşınmasını önler ve bu şekilde sorgu ve kod tekrarına engel
* olmuş olur. Yani asıl amaç veri işlem ve sorgulamaların tekrarlardan kaçınılarak merkezi bir yapıya çekilmesidir.
*/

class WordRepository(private val wordDao: WordDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}