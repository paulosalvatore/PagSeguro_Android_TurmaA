package br.com.paulosalvatore.pagseguroandroidturmaa.api

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RepositoryRetriever {
    private val service: GithubService

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        service = retrofit.create(GithubService::class.java)
    }

    fun getLanguageRepositories(
        callback: Callback<GithubRepositoriesResult>,
        language: String
    ) {
        val call = service.searchRepositories("language:$language")
        call.enqueue(callback)
    }
}
