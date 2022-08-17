package com.example.englanguagefinal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.englanguagefinal.model.topic.Success
import com.example.englanguagefinal.model.vocabulary.SuccessVocabulary
import com.example.englanguagefinal.model.exam.Question

@Database(entities = [Success::class, SuccessVocabulary::class, Question::class], version = 1)
abstract class VocabularyDatabase : RoomDatabase() {
    abstract fun topicDAO(): TopicDAO?
    abstract fun vocabularyDAO(): VocabularyDAO?
    abstract fun vocabularyOfTopicDAO(): VocabularyOfTopicDAO?
    abstract fun questionDao(): QuestionDAO

    companion object {
        private const val DATABASE_NAME = "iii.db"
        private var instance: VocabularyDatabase? = null

        @Synchronized
        fun getInstance(context: Context): VocabularyDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    VocabularyDatabase::class.java,
                    DATABASE_NAME
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance as VocabularyDatabase
        }
    }
}