package com.demo.networkpagination.utils

import com.demo.networkpagination.ui.adapter.CardsAdapter
import com.demo.networkpagination.ui.adapter.LoadStateAdapter
import org.koin.dsl.module

val appModule = module {
    single { CardsAdapter() }
    single { LoadStateAdapter(get()) }
}