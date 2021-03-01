package com.test.wn.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class CoinsViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
}