package com.example.playstationserver.model.local

import android.content.Context
import com.example.detection.utils.BooleanPreference
import com.example.detection.utils.StringPreference
import com.example.playstationserver.app.App

class LocalStorage {

    companion object {
        val pref = App.instance.getSharedPreferences("PlayStationSharedPref", Context.MODE_PRIVATE)
    }

    var playStation1_light_start by StringPreference(pref)
    var playStation1_light_stop by StringPreference(pref)
    var playStation2_light_start by StringPreference(pref)
    var playStation2_light_stop by StringPreference(pref)
    var playStation3_light_start by StringPreference(pref)
    var playStation3_light_stop by StringPreference(pref)

    var isPsLight1 by BooleanPreference(pref, false)
    var isPsLight2 by BooleanPreference(pref, false)
    var isPsLight3 by BooleanPreference(pref, false)

    var isFavLight1 by BooleanPreference(pref, true)
    var isFavLight2 by BooleanPreference(pref, true)
    var isFavLight3 by BooleanPreference(pref, true)
}