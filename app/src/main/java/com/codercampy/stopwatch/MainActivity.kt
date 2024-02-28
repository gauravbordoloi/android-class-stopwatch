package com.codercampy.stopwatch

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.codercampy.stopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {

        const val STATE_IDLE = 0
        const val STATE_START = 1
        const val STATE_STOP = 2

    }

    private lateinit var sharedPref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private lateinit var binding: ActivityMainBinding

    private var time: Long = 0
    private var laps = ""
    private var currentState: Int = STATE_IDLE

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.view_history) {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("laps", laps)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPref  = this.getSharedPreferences("abc", Context.MODE_PRIVATE)
        editor = sharedPref.edit()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val oldData = sharedPref.getString("laps", "")
        if (!oldData.isNullOrEmpty()) {
            binding.textLaps.text = oldData
        }

        binding.viewLaps.setOnClickListener {

            //Navigate to second activity
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("laps", laps)

            val student = Student("Purab", 25)
            val student2 = Student("Pragyan", 26)

            val students = ArrayList<Student>()
            students.add(student)
            students.add(student2)

            intent.putExtra("student", student)
            intent.putParcelableArrayListExtra(
                "students",
                students
            )

            startActivity(intent)

        }

        binding.btn1.setOnClickListener {
            when (currentState) {
                STATE_IDLE -> {
                    currentState = STATE_START
                    runHandler()
                    updateState()
                }

                STATE_START -> {
                    currentState = STATE_STOP
                    updateState()
                }

                STATE_STOP -> {
                    if (laps.isNotEmpty()) {
                        editor.putString("laps", laps)
                        editor.commit()

                        editor.putString("laps", null)
                    }

                    currentState = STATE_IDLE
                    updateState()
                }
            }
        }

        binding.btn2.setOnClickListener {
            lap()
        }

    }

    private fun lap() {
        if (laps.isNotEmpty()) {
            laps += "\n"
        }
        laps += parseTime()
        binding.textLaps.text = laps
    }

    private fun updateState() {
        when (currentState) {
            STATE_IDLE -> {
                binding.btn1.text = "Start"
                binding.btn1.setIconResource(R.drawable.play)
                binding.btn2.visibility = View.GONE

                binding.tvText.text = "00:00:000"
                //binding.textLaps.text = ""
                time = 0
                laps = ""
            }

            STATE_START -> {
                binding.btn1.text = "Stop"
                binding.btn1.setIconResource(R.drawable.stop)
                binding.btn2.visibility = View.VISIBLE
                binding.btn2.text = "Lap"
                binding.btn2.setIconResource(R.drawable.timer)
            }

            STATE_STOP -> {
                binding.btn1.text = "Reset"
                binding.btn1.setIconResource(R.drawable.restart)
                binding.btn2.visibility = View.GONE
            }
        }
    }

    private fun startTimer() {
        runHandler()
    }

    private fun runHandler() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (currentState == STATE_START) {
                time += 100
                binding.tvText.text = parseTime()

                runHandler()
            }
        }, 100)
    }

    private fun parseTime(): String {
        //1000 - > 00:01:000
        //1100 - > 00:01:100
        //3600 -> 00:03:600

        val millis = time % 1000
        val seconds = time / 1000
        val minutes = seconds / 60

        return "$minutes:$seconds:$millis"
    }

}