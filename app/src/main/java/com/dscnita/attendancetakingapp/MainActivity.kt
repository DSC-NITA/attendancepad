package com.dscnita.attendancetakingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.dscnita.attendancetakingapp.adapters.ClassAdapter
import com.dscnita.attendancetakingapp.databinding.ActivityMainBinding
import com.dscnita.attendancetakingapp.models.ClassItem

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val classItems = mutableListOf<ClassItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabMain.setOnClickListener {
            showDialogBox()
        }

        setToolBar()
    }

    private fun setToolBar()
    {
        val recyclerView=binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=ClassAdapter(this,classItems)

        val toolbar=binding.toolbar
        toolbar.toolbarTitle.text="Attendance Pad"
        toolbar.toolbarSubtitle.visibility=View.INVISIBLE
        toolbar.saveButton.visibility=View.INVISIBLE
        toolbar.backButton.visibility=View.INVISIBLE
    }

    private fun showDialogBox()
    {
        val builder=AlertDialog.Builder(this)
        val view=LayoutInflater.from(this).inflate(R.layout.class_dialogue,null)
        builder.setView(view)
        val dialog=builder.create()
        dialog.show()

        val cancelButton=view.findViewById<Button>(R.id.cancel_button)
        val addButton=view.findViewById<Button>(R.id.add_button)

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        addButton.setOnClickListener {
            addClass(view)
            dialog.dismiss()
        }
    }

    private fun addClass(view: View)
    {
        val className=view.findViewById<EditText>(R.id.className).text.toString()
        val subjectName=view.findViewById<EditText>(R.id.subjectName).text.toString()
        classItems.add(ClassItem(className,subjectName))
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }
}