package com.tihasg.mylist.ui.main

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tihasg.mylist.model.Pais
import com.tihasg.mylist.utils.Constants.ALEMANHA
import com.tihasg.mylist.utils.Constants.BRASIL

class MainViewModel : ViewModel() {
    var listener: MainListener? = null
    var list: MutableLiveData<List<Pais>> = MutableLiveData()

    fun setPaises() {
        list.postValue(listOf(Pais(BRASIL), Pais(ALEMANHA)))
        listener!!.buscarPaises()
    }

    fun buscar(view: View){
        listener!!.buscarPaises()
    }
}