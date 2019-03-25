package br.com.paulosalvatore.pagseguroandroidturmaa.model

import androidx.databinding.*
import androidx.databinding.library.baseAdapters.BR
import br.com.paulosalvatore.pagseguroandroidturmaa.utils.bindable

class Game(
    name: String,
    val launchYear: Int,
    val imageUrl: String,
    rating: Double
) : BaseObservable() {
    val isClassic = launchYear < 1990

    @get:Bindable var name by bindable(name, BR.name)
    @get:Bindable var rating by bindable(rating, BR.rating)

    /*var rating = rating
    @Bindable get
    set(value) {
        if (field != value) {
            field = value
            notifyPropertyChanged(BR.rating)
        }
    }*/
}
