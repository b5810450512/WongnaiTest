package com.test.wn.core.di

import com.test.wn.ui.viewmodel.CoinsViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

//fun provideApi() = BaseService.create()
//
//fun provideGson() = GsonBuilder().create()

val model = module {
    viewModel { CoinsViewModel(get()) }
}

val repository = module {
//    factory { DataDropDownRepository(get()) }
}

val remote = module {
//    factory { DataDropDownRemote(get()) }
}

val networkModule = module {
//    single { provideGson() }
//    single { provideApi() }
}

val listModules = listOf(
    repository,
    networkModule,
    model,
    remote
)