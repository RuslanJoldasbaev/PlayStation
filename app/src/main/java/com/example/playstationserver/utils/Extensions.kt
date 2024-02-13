package com.example.playstationserver.utils

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.playstationserver.app.App
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

fun String.toTime(): String {
    val simpleDateFormat = SimpleDateFormat("HH:mm", Locale.ROOT)
    simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT+5")
    return simpleDateFormat.format(this.toLong())
}


fun toast(message: String) {
    Toast.makeText(App.instance, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.snackBar(message: String) {
    Snackbar.make(this.requireView(), message, Snackbar.LENGTH_SHORT).show()
}

fun Int.milliSecondsToTimer() : String {
    val pattern = if (this >= 3_600_000L) "HH:mm:ss" else "mm:ss"
    val simpleDataFormat = SimpleDateFormat(pattern, Locale.ROOT)
    simpleDataFormat.timeZone = TimeZone.getTimeZone("GMT+0")
    return simpleDataFormat.format(this)
}

fun ViewPager2.autoScroll(interval: Long) {

    val handler = Handler(Looper.getMainLooper())
    var scrollPosition = 0

    val runnable = object : Runnable {

        override fun run() {

            val count = adapter?.itemCount ?: 0
            if (count != 0) setCurrentItem(scrollPosition++ % count, true)

            handler.postDelayed(this, interval)
        }
    }

    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            scrollPosition = position + 1
        }

        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(
            position: Int, positionOffset: Float, positionOffsetPixels: Int
        ) {
        }
    })

    handler.post(runnable)
}