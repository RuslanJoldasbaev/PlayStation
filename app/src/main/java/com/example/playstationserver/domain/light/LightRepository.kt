package com.example.playstationserver.domain.light

import com.example.playstationserver.dao.LightDao
import com.example.playstationserver.model.data.ResultData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class LightRepository(private val dao: LightDao) {
    suspend fun getAllLight() = flow {
        val list = dao.getAllLight()
        if (list.isNotEmpty()) {
            emit(ResultData.Success(list))
        } else {
            emit(ResultData.Message("Ba'ribir pustoy"))
        }
    }.catch {
        emit(ResultData.Error(it))
    }
}