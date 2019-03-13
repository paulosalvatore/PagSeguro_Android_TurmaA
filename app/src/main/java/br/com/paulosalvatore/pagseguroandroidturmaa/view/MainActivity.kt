package br.com.paulosalvatore.pagseguroandroidturmaa.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.paulosalvatore.pagseguroandroidturmaa.R
import br.com.paulosalvatore.pagseguroandroidturmaa.adapter.ProgrammingLanguageAdapter
import br.com.paulosalvatore.pagseguroandroidturmaa.adapter.RepositoryAdapter
import br.com.paulosalvatore.pagseguroandroidturmaa.api.GithubRepositoriesResult
import br.com.paulosalvatore.pagseguroandroidturmaa.api.RepositoryRetriever
import br.com.paulosalvatore.pagseguroandroidturmaa.domain.ProgrammingLanguage
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val repositoryRetriever = RepositoryRetriever()

    private val callback = object : Callback<GithubRepositoriesResult> {
        override fun onFailure(
            call: Call<GithubRepositoriesResult>,
            t: Throwable
        ) {
            longToast("Fail loading repositories.")

            Log.e("MainActivity", "Fail loading repositories", t)
        }

        override fun onResponse(
            call: Call<GithubRepositoriesResult>,
            response: Response<GithubRepositoriesResult>
        ) {
            longToast("Load finished.")

            if (response.isSuccessful) {
                response.body()?.repositories?.let {
                    recyclerView.adapter = RepositoryAdapter(it) { repository ->
                        longToast("Clicked item: ${repository.full_name}")
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val kotlin = ProgrammingLanguage(
                R.drawable.ic_developer_board,
                "Kotlin",
                2010,
                "Kotlin description"
        )

        val items = listOf(kotlin, kotlin)

        val adapter = ProgrammingLanguageAdapter(items) {
            //            longToast(it.title)
            repositoryRetriever.getLanguageRepositories(callback, it.title)
        }

        recyclerView.adapter = adapter
    }
}
