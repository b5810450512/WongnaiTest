package com.test.wn.core.di

import com.test.wn.core.service.BaseService
import com.test.wn.ui.repo.CoinListRemote
import com.test.wn.ui.repo.CoinsListRepository
import com.test.wn.ui.viewmodel.CoinsViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

fun provideApi() = BaseService.create()

val module = module {
    viewModel { CoinsViewModel(get()) }
}

val repository = module {
    factory { CoinsListRepository(get()) }
}

val remote = module {
    factory { CoinListRemote(get()) }
}

val networkModule = module {
    single { provideApi() }
}

val listModules = listOf(
    repository,
    networkModule,
    module,
    remote
)