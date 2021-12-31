package com.dscnita.attendancetakingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.dscnita.attendancetakingapp.adapters.ClassAdapter
import com.dscnita.attendancetakingapp.adapters.StudentAdapter
import com.dscnita.attendancetakingapp.databinding.ActivityStudentBinding
import com.dscnita.attendancetakingapp.models.ClassItem
import com.dscnita.attendancetakingapp.models.StudentItem

class StudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentBinding
    private val studentItems = mutableListOf<StudentItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent= intent
        val className=intent.getStringExtra("className").toString()
        val subjectName=intent.getStringExtra("subjectName").toString()

        val recyclerView=binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=StudentAdapter(this,studentItems)

        setToolBar(className,subjectName)
    }

    private fun setToolBar(className:String, subjectName:String)
    {
        val toolbar=binding.toolbar
        toolbar.toolbarTitle.text=className
        toolbar.toolbarSubtitle.text=subjectName

        toolbar.backButton.setOnClickListener {
            onBackPressed()
        }

        toolbar.menuButton.setOnClickListener {
            showAddStudentDialog()
        }
    }



    private fun showAddStudentDialog() {
        val builder= AlertDialog.Builder(this)
        val view= LayoutInflater.from(this).inflate(R.layout.student_dialog,null)
        builder.setView(view)
        val dialog=builder.create()
        dialog.show()

        val cancelButton=view.findViewById<Button>(R.id.cancel_button)
        val addButton=view.findViewById<Button>(R.id.add_button)

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        addButton.setOnClickListener {
            addStudent(view)
            dialog.dismiss()
        }
    }

        private fun addStudent(view:View)
    {
        val rollNo=view.findViewById<EditText>(R.id.rollNo).text.toString()
        val studentName=view.findViewById<EditText>(R.id.studentName).text.toString()
        studentItems.add(StudentItem(rollNo,studentName))
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }
}