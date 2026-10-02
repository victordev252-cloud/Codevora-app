package com.example.clockapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.chrono.HijriChronology
import java.time.format.DateTimeFormatter
import java.util.Locale

data class TimeState(
    val currentTime: String = "",
    val gregorianDate: String = "",
    val hijriDate: String = "",
    val dayOfWeek: String = ""
)

class ClockViewModel : ViewModel() {

    private val _timeState = MutableStateFlow(TimeState())
    val timeState: StateFlow<TimeState> = _timeState

    init {
        startClock()
    }

    private fun startClock() {
        viewModelScope.launch {
            while (true) {
                val nowTime = LocalTime.now()
                val nowDate = LocalDate.now()

                // Qaabka Saacadda (HH:mm:ss)
                val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
                val formattedTime = nowTime.format(timeFormatter)

                // Taariikhda Miilaadiga
                val dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.getDefault())
                val formattedGregorian = nowDate.format(dateFormatter)

                // Maalinta
                val dayFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.getDefault())
                val formattedDay = nowDate.format(dayFormatter)

                // Taariikhda Hijriga (Muslimka)
                val hijriDateToday = HijriChronology.INSTANCE.date(nowDate)
                val hijriFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH)
                val formattedHijri = "${hijriDateToday.format(hijriFormatter)} AH"

                _timeState.value = TimeState(
                    currentTime = formattedTime,
                    gregorianDate = formattedGregorian,
                    hijriDate = formattedHijri,
                    dayOfWeek = formattedDay
                )

                delay(1000) // Wuxuu cusboonaanayaa 1 saniye walba
            }
        }
    }
}
