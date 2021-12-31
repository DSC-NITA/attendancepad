package com.dscnita.attendancetakingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.dscnita.attendancetakingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabMain.setOnClickListener {
            showDialogBox()
        }
    }

    private fun showDialogBox()
    {
        val builder=AlertDialog.Builder(this)
        val view=LayoutInflater.from(this).inflate(R.layout.class_dialogue,null)
        builder.setView(view)
        builder.create().show()
    }
}