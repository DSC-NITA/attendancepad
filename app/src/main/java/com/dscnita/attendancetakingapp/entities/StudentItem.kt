package com.dscnita.attendancetakingapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StudentItem (
    val c_id: Int,
    val rollNo: String,
    val name: String,
    var status: Boolean = true){
    @PrimaryKey(autoGenerate = true) var s_id: Int = 0
}