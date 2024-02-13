package com.example.playstationserver.presentation.light

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.playstationserver.dao.LightDao
import com.example.playstationserver.domain.light.LightRepository
import com.example.playstationserver.model.PlayStationDatabase
import com.example.playstationserver.model.data.ResultData
import com.example.playstationserver.model.data.light.LightData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LightViewModel(application: Application) : AndroidViewModel(application) {
    private var dao: LightDao
    var repo: LightRepository

    init {
        dao = PlayStationDatabase.getInstance(application).getLightDao()
        repo = LightRepository(dao)
    }

    val getAllLightFLow = MutableSharedFlow<List<LightData>>()
    val messageFlow = MutableSharedFlow<String>()
    val errorFlow = MutableSharedFlow<Throwable>()


    suspend fun getAllLight() {
        repo.getAllLight().onEach {
            when (it) {
                is ResultData.Success -> {
                    getAllLightFLow.emit(it.data)
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