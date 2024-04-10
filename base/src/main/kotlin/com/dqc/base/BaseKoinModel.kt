package com.dqc.base

import com.dqc.base.presentation.nav.NavManager
import org.koin.dsl.module

val baseModule = module {

    single { NavManager() }
}