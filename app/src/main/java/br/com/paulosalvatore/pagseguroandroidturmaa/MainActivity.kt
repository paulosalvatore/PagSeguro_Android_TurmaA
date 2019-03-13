package br.com.paulosalvatore.pagseguroandroidturmaa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

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
//            Toast.makeText(this, it.title, Toast.LENGTH_LONG).show()
            longToast(it.title)
        }

        recyclerView.adapter = adapter

        alert("Message", "Title") {
            yesButton {
                toast("Yes Button")
            }
            noButton {
                toast("No Button")
            }
        }.show()

        doAsync {
            val imagem = "CarregarImagem"
            uiThread {
                print(imagem)
            }
        }
    }
}
class Posicao(val latitude: Double)
