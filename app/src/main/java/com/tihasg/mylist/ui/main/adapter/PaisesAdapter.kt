package com.tihasg.mylist.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tihasg.mylist.R
import com.tihasg.mylist.model.Pais
import com.tihasg.mylist.utils.Constants.ALEMANHA
import com.tihasg.mylist.utils.Constants.BRASIL
import kotlin.properties.Delegates

class PaisesAdapter : RecyclerView.Adapter<PaisesViewHolder>(), Filterable {

    var items: List<Pais> by Delegates.observable(emptyList()) { _, old, new -> if (old != new) notifyDataSetChanged() }

    var paisList: List<Pais> by Delegates.observable(emptyList()) { _, old, new ->
        if (old != new) notifyDataSetChanged()
    }
    var filterList = ArrayList<Pais>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaisesViewHolder {
        return PaisesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_paises, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PaisesViewHolder, position: Int) {
        val atual = items[position]
        holder.bind(item = atual)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                filterList = if (charSearch.isEmpty()) {
                    paisList as ArrayList<Pais>
                } else {
                    val resultList = ArrayList<Pais>()
                    for (row in paisList) {
                        if (row.name.toLowerCase().contains(constraint.toString().toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                try {
                    items = results?.values as List<Pais>
                    notifyDataSetChanged()
                } catch (e: Exception) {
                    items = paisList
                    notifyDataSetChanged()
                }
            }
        }
    }

}

class PaisesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: Pais) {
        val namePais = itemView.findViewById<TextView>(R.id.tv_name_paises)
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)

        with(itemView) {
           namePais.text = item.name

            if (item.name == BRASIL){
               imageView.background = context.resources.getDrawable(R.drawable.brasil)
            }

            if (item.name == ALEMANHA){
                imageView.background = context.resources.getDrawable(R.drawable.alemanha)
            }
        }
    }
}