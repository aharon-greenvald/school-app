package com.example.mybinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dayFragment = DayFragment()
        val monthFragment = MonthFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentDay,dayFragment)
//            replace(R.id.fragmentMonth,monthFragment)
                    .commit()
        }











    }


    }



