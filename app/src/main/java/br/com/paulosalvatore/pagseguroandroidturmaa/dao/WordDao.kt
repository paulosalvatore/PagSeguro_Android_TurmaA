package br.com.paulosalvatore.pagseguroandroidturmaa.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.paulosalvatore.pagseguroandroidturmaa.entities.Word

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: Word)

    @Query("DELETE FROM word_table")
    fun deleteAll()

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAllWords(): LiveData<List<Word>>

    @Update
    fun update(word: Word): Int

    @Delete
    fun delete(word: Word)
}
