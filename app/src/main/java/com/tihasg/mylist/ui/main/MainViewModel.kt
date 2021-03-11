package com.tihasg.mylist.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tihasg.mylist.model.Pais
import com.tihasg.mylist.utils.Constants.ALEMANHA
import com.tihasg.mylist.utils.Constants.BRASIL

class MainViewModel : ViewModel() {
    var listener: MainListener? = null

    val list: MutableLiveData <List<Pais>> = MutableLiveData()

    fun initViewModel(){
        list.postValue(listOf(Pais(BRASIL), Pais(ALEMANHA)))
        listener!!.onSetList()
        listener!!.onSearch()
    }
}