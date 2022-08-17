package com.example.englanguagefinal.database
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.englanguagefinal.model.topic.Success
import com.example.englanguagefinal.model.vocabulary.SuccessVocabulary
import com.example.englanguagefinal.model.exam.Question

@Dao
interface TopicDAO {

    @Insert
    fun insertTopic(success: Success)

    @Query("SELECT * FROM topics")
    fun getListTopic() : List<Success>
}

@Dao
interface VocabularyDAO {

    @Insert
    fun insertVocabulary(successVocabulary: SuccessVocabulary)

    @Query("SELECT * FROM vocabulary")
    fun getListVocabulary() : List<SuccessVocabulary>
}

@Dao
interface VocabularyOfTopicDAO {

    @Insert
    fun insertVocabularyOfTopic(successVocabulary: List<SuccessVocabulary>)

    @Query("SELECT * FROM vocabulary")
    fun getListVocabularyOfTopic() : List<SuccessVocabulary>
}

@Dao
interface QuestionDAO {

    @Insert
    fun insertQuestion(question: Question)

    @Query("SELECT * FROM question_table")
    fun getAllQuestions() : LiveData<List<Question>>

    @Query("DELETE FROM question_table")
    fun deleteAllQuestions()
}