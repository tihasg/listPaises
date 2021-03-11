package com.tihasg.mylist.di

import com.tihasg.mylist.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {MainViewModel() }
}