package com.tihasg.mylist.ui.main

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var listener: MainListener? = null

    fun setPais(){
        listener!!.setListaPaises()
    }
}