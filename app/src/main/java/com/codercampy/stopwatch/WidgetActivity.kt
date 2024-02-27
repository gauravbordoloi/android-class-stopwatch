package com.codercampy.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.SeekBar
import android.widget.Toast
import com.bumptech.glide.Glide
import com.codercampy.stopwatch.databinding.ActivityWidgetBinding

class WidgetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWidgetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWidgetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Load an image
        //binding.imageView.setImageResource(R.drawable.codercampy)

        binding.switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Glide.with(binding.imageView)
                    .load("https://img.freepik.com/free-photo/painting-mountain-lake-with-mountain-background_188544-9126.jpg")
                    .into(binding.imageView)
            } else {
                binding.imageView.setImageResource(R.drawable.codercampy)
            }
        }

        binding.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(this, isChecked.toString(), Toast.LENGTH_SHORT).show()
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.tv.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

//        binding.webview.settings.javaScriptEnabled = true
//        binding.webview.loadUrl("https://codercampy.com")

    }

}