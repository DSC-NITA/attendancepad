package com.dscnita.attendancetakingapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ClassItem (
    val className: String,
    val subjectName: String
    ){
    @PrimaryKey(autoGenerate = true) var c_id: Int = 0
}