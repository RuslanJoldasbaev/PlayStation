package com.example.playstationserver.presentation.night

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.playstationserver.dao.NightDao
import com.example.playstationserver.domain.night.NightRepository
import com.example.playstationserver.model.PlayStationDatabase
import com.example.playstationserver.model.data.ResultData
import com.example.playstationserver.model.data.night.NightData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class NightViewModel(application: Application) : AndroidViewModel(application) {
    private var dao: NightDao
    var repo : NightRepository

    init {
        dao = PlayStationDatabase.getInstance(application).getNightDao()
        repo = NightRepository(dao)
    }

    val getAllLanguageFlow = MutableSharedFlow<List<NightData>>()
    val messageFlow = MutableSharedFlow<String>()
    val errorFlow = MutableSharedFlow<Throwable>()

    suspend fun getAllLanguage() {
        repo.getAllNight().onEach {
            when (it) {
                is ResultData.Success -> {
                    getAllLanguageFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    messageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    errorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}