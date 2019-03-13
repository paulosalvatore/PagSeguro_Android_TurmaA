package br.com.paulosalvatore.pagseguroandroidturmaa.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.paulosalvatore.pagseguroandroidturmaa.R
import br.com.paulosalvatore.pagseguroandroidturmaa.adapter.ProgrammingLanguageAdapter
import br.com.paulosalvatore.pagseguroandroidturmaa.domain.ProgrammingLanguage
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast

class MainActivity : AppCompatActivity() {

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
            longToast(it.title)
        }

        recyclerView.adapter = adapter
    }
}
