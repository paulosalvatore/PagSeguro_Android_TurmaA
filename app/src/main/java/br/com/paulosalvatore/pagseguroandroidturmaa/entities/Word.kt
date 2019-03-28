package br.com.paulosalvatore.pagseguroandroidturmaa.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table") // tableName é opcional
data class Word(
    @PrimaryKey
    @ColumnInfo(name = "word") // opcional
    val word: String
)
