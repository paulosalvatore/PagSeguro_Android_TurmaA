package br.com.paulosalvatore.pagseguroandroidturmaa.domain

import androidx.annotation.DrawableRes

data class ProgrammingLanguage(
    @DrawableRes
    val imageResourceId: Int,
    val title: String,
    val year: Int,
    val description: String
)
