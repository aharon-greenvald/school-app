package com.example.mybinding

import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.month_fragment.*
import java.text.SimpleDateFormat
import java.time.YearMonth
import java.time.temporal.WeekFields
import java.util.*

class MonthFragment : Fragment(R.layout.month_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentMonth = YearMonth.now()
        val firstMonth = currentMonth.minusMonths(1)
        val lastMonth = currentMonth.plusMonths(1)
        val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
        calendarView.setup(firstMonth, lastMonth, firstDayOfWeek)
        calendarView.scrollToMonth(currentMonth)

        calendarView.dayBinder = object : DayBinder<DayViewContainer> {


            // Called only when a new container is needed.
            override fun create(view: View) = DayViewContainer(view)

            // Called every time we need to reuse a container.
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                with(container) {
                    textView.text = day.date.dayOfMonth.toString()
                    if (day.owner == DayOwner.THIS_MONTH) {
                        container.textView.setTextColor(Color.WHITE)
                    } else {
                        container.textView.setTextColor(Color.GRAY)
                    }

//                    textView.setTextColor(0xff0000ff)
                    textView.setOnClickListener {
                        val cal = Calendar.getInstance()
                        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                            cal.set(Calendar.HOUR_OF_DAY, hour)
                            cal.set(Calendar.MINUTE, minute)
                            var m= "${SimpleDateFormat("HH:mm").format(cal.time)} ${day.date.year} " +
                                    " ${day.date.month}  ${day.date.dayOfWeek.toString()}"

                            textView.setTextColor(0xff000000)
                            val intent = Intent(activity, EventActivity::class.java).apply {
                                putExtra(EXTRA_MESSAGE, m)

                            }

                            startActivity(intent)

                        }
                        TimePickerDialog(activity, timeSetListener, cal.get(Calendar.HOUR_OF_DAY),
                                cal.get(Calendar.MINUTE), true).show()
                    }


                }


            }

        }    }
}