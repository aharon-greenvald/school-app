package com.example.mybinding

import android.view.View
import android.widget.TextView
import com.kizitonwose.calendarview.ui.ViewContainer


class DayViewContainer(view: View) : ViewContainer(view) {
    val textView = view.findViewById<TextView>(R.id.calendarDayText)
    val textView3 = view.findViewById<TextView>(R.id.calendarMonthText )




    fun TextView.mySetTextColor(color: Long) = this.setTextColor(color.toInt())


}

