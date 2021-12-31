package com.dscnita.attendancetakingapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.dscnita.attendancetakingapp.R
import com.dscnita.attendancetakingapp.StudentActivity
import com.dscnita.attendancetakingapp.models.ClassItem
import com.dscnita.attendancetakingapp.models.StudentItem

class ClassAdapter(
    private val context: Context,
    private val dataset: MutableList<ClassItem>
) : RecyclerView.Adapter<ClassAdapter.ClassViewHolder> (){


    class ClassViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val className: TextView =view.findViewById(R.id.className)
        val subjectName: TextView =view.findViewById(R.id.subjectName)
        val button:Button=view.findViewById(R.id.takeAttendance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.class_item, parent, false)

        return ClassViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        holder.className.text = dataset[position].className
        holder.subjectName.text = dataset[position].subjectName
        holder.button.setOnClickListener {
            val intent= Intent(context,StudentActivity::class.java)
            intent.putExtra("className",dataset[position].className)
            intent.putExtra("subjectName",dataset[position].subjectName)
            intent.putExtra("position",position)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}