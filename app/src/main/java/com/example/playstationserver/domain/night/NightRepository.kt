package com.example.playstationserver.domain.night

import com.example.playstationserver.dao.NightDao
import com.example.playstationserver.model.data.ResultData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class NightRepository(private val dao: NightDao) {
    suspend fun getAllNight() = flow {
        val list = dao.getAllNight()
        if (list.isNotEmpty()) {
            emit(ResultData.Success(list))
        } else {
            emit(ResultData.Message("Ba'ribir pustoy"))
        }
    }.catch {
        emit(ResultData.Error(it))
    }
}