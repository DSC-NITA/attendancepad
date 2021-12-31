package com.dscnita.attendancetakingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dscnita.attendancetakingapp.databinding.ActivityMainBinding
import com.dscnita.attendancetakingapp.databinding.ActivityStudentBinding

class StudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}