package br.com.paulosalvatore.pagseguroandroidturmaa.api

import com.squareup.moshi.Json

data class GithubRepositoriesResult(
        @field:Json(name = "items")
//        @SerializedName("items")
        val repositories: List<Repository>
)

data class Repository(
        val id: Long?,
        val name: String?,
        val full_name: String?,
        val description: String?,
        val owner: Owner?
)

data class Owner(
        val login: String?,
        val id: Long?,
        val avatar_url: String?
)
