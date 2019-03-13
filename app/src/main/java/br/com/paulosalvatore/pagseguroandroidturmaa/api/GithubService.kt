package br.com.paulosalvatore.pagseguroandroidturmaa.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("search/repositories")
    fun searchRepositories(
            @Query("q") query: String,
            @Query("sort") sort: String = "stars",
            @Query("order") order: String = "desc"
    ): Call<GithubRepositoriesResult>
}
