package br.com.paulosalvatore.pagseguroandroidturmaa.api

import com.squareup.moshi.Json

data class GithubRepositoriesResult(
        @Json(name = "items")
        val repositories: List<Repository>
)

class Repository
