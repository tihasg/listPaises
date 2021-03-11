package com.tihasg.mylist.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.tihasg.mylist.R
import com.tihasg.mylist.databinding.ActivityMainBinding
import com.tihasg.mylist.ui.main.adapter.PaisesAdapter
import com.tihasg.mylist.model.Pais
import com.tihasg.mylist.utils.Constants.ALEMANHA
import com.tihasg.mylist.utils.Constants.BRASIL
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainListener {
    private var adapterPais = PaisesAdapter()
    private var listPais: RecyclerView? = null

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        listPais = findViewById(R.id.paisList)
        setupRecyclerView()
        adapterPais.notifyDataSetChanged()
    }

    private fun setupRecyclerView() {
        listPais?.adapter = adapterPais
        val manager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity)
        listPais?.layoutManager = manager
    }

    private fun initViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel =
            ViewModelProviders.of(this, ViewModelFactory()).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.listener = this
        viewModel.setPaises()

        val observerCaio = Observer<List<Pais>> {
            adapterPais.items = it
            adapterPais.paisList = it
        }
        viewModel.list.observe(this, observerCaio)
    }


    override fun buscarPaises() {
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
}