package br.com.paulosalvatore.pagseguroandroidturmaa.chronometervm

import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import br.com.paulosalvatore.pagseguroandroidturmaa.R
import kotlinx.android.synthetic.main.activity_viewmodel.*

class ViewModelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewmodel)

        val chronometerViewModel =
            ViewModelProviders
                .of(this)
                .get(ChronometerViewModel::class.java)

        if (chronometerViewModel.startTime == 0L) {
            val startTime = SystemClock.elapsedRealtime()
            chronometerViewModel.startTime = startTime
            chronometer.base = startTime
        } else {
            chronometer.base = chronometerViewModel.startTime
        }

        chronometer.start()
    }
}
