package br.com.paulosalvatore.pagseguroandroidturmaa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Flowables
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val observable =
            Observable
                .just(1, 2, 3)
                .map { it * 10 }
                .filter { it < 30 }

        val observer = object : Observer<Int> {
            override fun onComplete() {
                println("onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                println("onSubscribe $d")
            }

            override fun onNext(t: Int) {
                println("onNext $t")
            }

            override fun onError(e: Throwable) {
                println("onError $e")
            }
        }

        val consumer = object : Consumer<Int> {
            override fun accept(t: Int) {
                println("accept $t")
            }
        }

        observable.subscribe(observer)
        val disposable = observable.subscribe(consumer)*/

        val nameChangeObservable =
                RxTextView
                        .textChanges(etName)
                        .skipInitialValue()
                        .debounce(500, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .toFlowable(BackpressureStrategy.LATEST)

        val yearChangeObservable =
                RxTextView
                        .textChanges(etYear)
                        .skipInitialValue()
                        .debounce(500, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .toFlowable(BackpressureStrategy.LATEST)

        val imageUrlChangeObservable =
                RxTextView
                        .textChanges(etImageUrl)
                        .skipInitialValue()
                        .debounce(500, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .toFlowable(BackpressureStrategy.LATEST)

        val ratingChangeObservable =
                RxTextView
                        .textChanges(etRating)
                        .skipInitialValue()
                        .debounce(500, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .toFlowable(BackpressureStrategy.LATEST)

        disposable = Flowables.combineLatest(
                nameChangeObservable,
                yearChangeObservable,
                imageUrlChangeObservable,
                ratingChangeObservable
        ) { newName, newYear, newImageUrl, newRating ->
            val nameValid = newName.length > 3
            if (!nameValid) {
                etName.error = "Invalid name"
            }

            val yearValid = newYear.length == 4
            if (!yearValid) {
                etYear.error = "Invalid year"
            }

            val imageUrlValid = newImageUrl.length > 5
            if (!imageUrlValid) {
                etImageUrl.error = "Invalid Image URL"
            }

            val ratingValid = newRating.length > 1
            if (!ratingValid) {
                etRating.error = "Invalid Rating"
            }

            nameValid && yearValid && imageUrlValid && ratingValid
        }.subscribe {
            btAdd.setBackgroundColor(
                    ContextCompat.getColor(
                            applicationContext,
                            if (it)
                                R.color.colorPrimary
                            else
                                android.R.color.darker_gray
                    )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        disposable.dispose()
    }
}
