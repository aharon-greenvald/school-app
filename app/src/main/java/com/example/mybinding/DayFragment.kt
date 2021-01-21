package com.example.mybinding

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import android.icu.util.HebrewCalendar
import android.os.Bundle
import android.view.View
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import kotlinx.android.synthetic.main.calander_month.*
import kotlinx.android.synthetic.main.calendar_day_layout.view.*
import kotlinx.android.synthetic.main.day_fragment.*
import kotlinx.android.synthetic.main.month_fragment.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.temporal.WeekFields
import java.util.*

class DayFragment : Fragment(R.layout.day_fragment) {


    var color = "0xff0000ff"
     var selectedDate: CalendarDay?=null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarView.monthHeaderBinder = object : MonthHeaderFooterBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)

            override fun bind(container: DayViewContainer, month: CalendarMonth) {
                container.textView3.text = month.yearMonth.month.toString()


            }

        }

        val current = LocalDateTime.now()
        val hebrew = HebrewCalendar.TAMUZ;

        val currentMonth = YearMonth.now()
        val firstMonth = currentMonth.minusMonths(1)
        val lastMonth = currentMonth.plusMonths(1)
        val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
        calendarView.setup(currentMonth, lastMonth, firstDayOfWeek)
        calendarView.scrollToMonth(currentMonth)
        calendarView.orientation = HORIZONTAL


        calendarView.dayBinder = object : DayBinder<DayViewContainer> {


            // Called only when a new container is needed.
            override fun create(view: View) = DayViewContainer(view)

            // Called every time we need to reuse a container.
            @SuppressLint("NewApi", "SetTextI18n")
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                if (day.date.month == current.month && day.date.dayOfMonth == current.dayOfMonth) {
                    container.textView.setBackgroundColor(Color.CYAN)
                }
                else if (day== selectedDate){
                                    container.textView.setBackgroundColor(Color.RED)

                }else{
                    container.textView.setBackgroundColor(Color.WHITE)

                }



                with(container) {
                    textView.text = day.date.dayOfMonth.toString()
                    if (day.owner == DayOwner.THIS_MONTH) {
                        textView.setTextColor(Color.BLACK)
                    } else {
                        textView.setTextColor(Color.BLUE)
                    }
                    textView.setOnClickListener {
                        selectedDate = day

                        textView.setBackgroundColor(Color.RED)
//                        calendarView.notifyDayChanged(day)
                        calendarView.notifyMonthChanged(currentMonth)




                        timePicker1.setOnTimeChangedListener { view, hourOfDay, minute ->



                            t.text = "$hourOfDay : $minute in ${textView.text} "
//                            t.text = selectedDate
                        }
                    }
                }
            }
        }
    }
}