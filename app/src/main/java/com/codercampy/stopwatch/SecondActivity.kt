package com.codercampy.stopwatch

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codercampy.stopwatch.databinding.ActivitySecondBinding

class SecondActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //receive the data from 1st activity
        val data = intent.getStringExtra("laps")
        //binding.textLaps.text = data

        val student = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("student", Student::class.java)
        } else {
            intent.getParcelableExtra("student")
        }

        val students = intent.getParcelableArrayListExtra<Student>("students")

        binding.textLaps.text = students.toString()

    }

}