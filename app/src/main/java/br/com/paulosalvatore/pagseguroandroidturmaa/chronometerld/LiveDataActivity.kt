package br.com.paulosalvatore.pagseguroandroidturmaa.chronometerld

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.paulosalvatore.pagseguroandroidturmaa.R
import kotlinx.android.synthetic.main.activity_live_data.*

class LiveDataActivity : AppCompatActivity() {

    private val liveDataTimerViewModel by lazy {
        ViewModelProviders.of(this).get(LiveDataTimerViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        subcribe()
    }

    @SuppressLint("SetTextI18n")
    fun subcribe() {
        val elapsedTimeObserver = Observer<Long> { time ->
            textView.text = "$time seconds"
        }

        liveDataTimerViewModel.elapsedTime.observe(this, elapsedTimeObserver)
    }
}
