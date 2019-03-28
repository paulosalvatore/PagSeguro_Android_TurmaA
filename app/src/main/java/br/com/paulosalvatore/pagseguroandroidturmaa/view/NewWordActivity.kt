package br.com.paulosalvatore.pagseguroandroidturmaa.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.paulosalvatore.pagseguroandroidturmaa.R
import kotlinx.android.synthetic.main.activity_new_word.*

class NewWordActivity : AppCompatActivity() {

    companion object {
        const val WORD_KEY = "WORD"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        btSave.setOnClickListener {
            val replyIntent = Intent()
            if (etWord.text.isEmpty()) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                replyIntent.putExtra(WORD_KEY, etWord.text.toString())
                setResult(Activity.RESULT_OK, replyIntent)
            }

            finish()
        }
    }
}
