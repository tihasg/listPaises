package com.tihasg.mylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import com.tihasg.mylist.adapter.PaisesAdapter
import com.tihasg.mylist.model.Pais
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapterPais = PaisesAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pais: List<Pais> = listOf(Pais("Brasil"),Pais("Alemanha") )
        adapterPais.items = pais
        adapterPais.paisList = pais
        setupRecyclerView()
        adapterPais.notifyDataSetChanged()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
               adapterPais.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapterPais.filter.filter(newText)
                return true
            }

        })
    }

    private fun setupRecyclerView() {
        with(paisList) {
            adapter = adapterPais
            val manager = androidx.recyclerview.widget.LinearLayoutManager(context)
            layoutManager = manager
        }
    }
}