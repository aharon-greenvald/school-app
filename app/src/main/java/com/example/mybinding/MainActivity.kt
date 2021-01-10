package com.example.mybinding

import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.ui.DayBinder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.calendar_day_layout.*
import java.text.SimpleDateFormat
import java.time.YearMonth
import java.time.temporal.WeekFields
import java.util.*
const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentMonth = YearMonth.now()
        val firstMonth = currentMonth.minusMonths(2)
        val lastMonth = currentMonth.plusMonths(2)
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

                    textView.setTextColor(0xff0000ff)
                    textView.setOnClickListener {
                        val cal = Calendar.getInstance()
                        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                            cal.set(Calendar.HOUR_OF_DAY, hour)
                            cal.set(Calendar.MINUTE, minute)
                            var m= "${SimpleDateFormat("HH:mm").format(cal.time)} ${day.date.year} " +
                                    " ${day.date.month}  ${day.date.dayOfMonth.toString()}"

                            textView.setTextColor(0xff000000)
                            val intent = Intent(this@MainActivity, EventActivity::class.java).apply {
                                putExtra(EXTRA_MESSAGE, m)

                            }

                            startActivity(intent)

                        }
                        TimePickerDialog(this@MainActivity, timeSetListener, cal.get(Calendar.HOUR_OF_DAY),
                                cal.get(Calendar.MINUTE), true).show()
//
                    }

//                        Toast.makeText(this@MainActivity,"the date is${textView.text} " ,Toast.LENGTH_SHORT).show()

                    }
//                sendMessage()


                }

            }


        }


    }



