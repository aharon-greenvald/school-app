package com.example.mybinding

import android.view.View
import android.widget.TextView
import com.kizitonwose.calendarview.ui.ViewContainer


class DayViewContainer(view: View) : ViewContainer(view) {
    val textView = view.findViewById<TextView>(R.id.calendarDayText)


    fun TextView.setTextColor(color: Long) = this.setTextColor(color.toInt())


}