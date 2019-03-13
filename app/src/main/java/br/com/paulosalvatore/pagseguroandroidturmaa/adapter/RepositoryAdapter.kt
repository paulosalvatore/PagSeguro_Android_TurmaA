package br.com.paulosalvatore.pagseguroandroidturmaa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.paulosalvatore.pagseguroandroidturmaa.R
import br.com.paulosalvatore.pagseguroandroidturmaa.api.Repository
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.repository_item.view.*

class RepositoryAdapter(
    private val items: List<Repository>,
    private val listener: (Repository) -> Unit
) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repository_item, parent, false)

        return ViewHolder(view, listener)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindView(item)
    }

    class ViewHolder(
        itemView: View,
        val listener: (Repository) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bindView(item: Repository) = with(itemView) {
            val avatar_url = item.owner?.avatar_url
            Glide.with(itemView).load(avatar_url).into(ivMain)
//            ivMain.setImageResource(avatar_url)

            tvTitle.text = item.name
            tvOwner.text = item.owner?.login
            tvDescription.text = item.description

            setOnClickListener { listener(item) }
        }
    }
}
