package com.tihasg.mylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.tihasg.mylist.adapter.PaisesAdapter
import com.tihasg.mylist.model.Pais
import com.tihasg.mylist.utils.Constants.ALEMANHA
import com.tihasg.mylist.utils.Constants.BRASIL
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var adapterPais = PaisesAdapter()
    private var listPais: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listPais = findViewById(R.id.paisList)
        val pais: List<Pais> = listOf(Pais(BRASIL), Pais(ALEMANHA))


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
        listPais?.adapter = adapterPais
        val manager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity)
        listPais?.layoutManager = manager
    }
}