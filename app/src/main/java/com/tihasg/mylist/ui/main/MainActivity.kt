package com.tihasg.mylist.ui.main

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.tihasg.mylist.R
import com.tihasg.mylist.adapter.PaisesAdapter
import com.tihasg.mylist.databinding.ActivityMainBinding
import com.tihasg.mylist.model.Pais

class MainActivity : AppCompatActivity(), MainListener {
    private var adapterPais = PaisesAdapter()
    private var listPais: RecyclerView? = null

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        setupRecyclerView()
    }

    private fun initViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel =
            ViewModelProviders.of(this, ViewModelFactory()).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.listener = this

        listPais = binding.paisList

        viewModel.initViewModel()


    }

    private fun setupRecyclerView() {
        listPais?.adapter = adapterPais
        val manager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity)
        listPais?.layoutManager = manager
    }

    override fun onSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

    override fun onSetList() {
        val observableList = Observer<List<Pais>> { pais ->
            adapterPais.items = pais
            adapterPais.paisList = pais
            adapterPais.notifyDataSetChanged()
        }
        viewModel.list.observe(this, observableList)
    }
}