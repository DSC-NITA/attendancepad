package com.dscnita.attendancetakingapp.entities.relations

import androidx.lifecycle.LiveData
import androidx.room.Embedded
import androidx.room.Relation
import com.dscnita.attendancetakingapp.entities.ClassItem
import com.dscnita.attendancetakingapp.entities.StudentItem

class ClassItemWithStudentItems(
    @Embedded val classItem: ClassItem,
    @Relation(
        parentColumn = "c_id",
        entityColumn = "c_id"
    )
    val studentItems: List<StudentItem>
)