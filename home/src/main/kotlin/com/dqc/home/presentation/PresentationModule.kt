package com.dqc.home.presentation

import org.koin.androidx.viewmodel.dsl.viewModelOf
import com.dqc.home.presentation.screen.home.HomeViewModel
import org.koin.dsl.module

internal val presentationModule = module {
    viewModelOf(::HomeViewModel)

    single { }

}