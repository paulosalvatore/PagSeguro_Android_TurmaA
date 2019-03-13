package br.com.paulosalvatore.pagseguroandroidturmaa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.programming_language_item.view.*

class ProgrammingLanguageAdapter(
    private val items: List<ProgrammingLanguage>,
    private val listener: (ProgrammingLanguage) -> Unit
) : RecyclerView.Adapter<ProgrammingLanguageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.programming_language_item, parent, false)

        return ViewHolder(view, listener)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindView(item)
    }

    class ViewHolder(
        itemView: View,
        val listener: (ProgrammingLanguage) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bindView(item: ProgrammingLanguage) = with(itemView) {
            ivMain.setImageResource(item.imageResourceId)
            tvTitle.text = item.title
            tvLaunchYear.text = item.year.toString()
            tvDescription.text = item.description

            setOnClickListener { listener(item) }
        }
    }
}
