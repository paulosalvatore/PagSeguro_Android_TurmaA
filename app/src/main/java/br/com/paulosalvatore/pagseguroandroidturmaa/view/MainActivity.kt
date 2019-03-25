package br.com.paulosalvatore.pagseguroandroidturmaa.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableDouble
import br.com.paulosalvatore.pagseguroandroidturmaa.model.Game
import br.com.paulosalvatore.pagseguroandroidturmaa.R
import br.com.paulosalvatore.pagseguroandroidturmaa.databinding.ActivityMainBinding
import br.com.paulosalvatore.pagseguroandroidturmaa.utils.BindingAdapters
import br.com.paulosalvatore.pagseguroandroidturmaa.utils.SetContentView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by SetContentView(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        binding = DataBindingUtil.setContentView(
//            this,
//            R.layout.activity_main
//        )

        val game = Game(
            "Donkey Kong",
            1981,
            "https://images-submarino.b2w.io/produtos/01/00/sku/31839/5/31839531_1SZ.jpg",
            4.0
        )
        binding.game = game

        tvRating.setOnClickListener {
            game.rating += 0.1
            Toast.makeText(this, game.rating.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}
