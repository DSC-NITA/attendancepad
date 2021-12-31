package com.dscnita.attendancetakingapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dscnita.attendancetakingapp.R
import com.dscnita.attendancetakingapp.models.StudentItem

class StudentAdapter(
    private val context: Context,
    private val dataset: MutableList<StudentItem>
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder> (){


    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rollNo: TextView =view.findViewById(R.id.rollNo)
        val studentName: TextView =view.findViewById(R.id.studentName)
        val status:TextView=view.findViewById(R.id.status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false)

        return StudentViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.rollNo.text = dataset[position].rollNo
        holder.studentName.text = dataset[position].name
        holder.status.text=dataset[position].status
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}