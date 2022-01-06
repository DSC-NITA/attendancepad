package com.dscnita.attendancetakingapp.repository

import androidx.lifecycle.LiveData
import com.dscnita.attendancetakingapp.dao.AttendanceDao
import com.dscnita.attendancetakingapp.entities.ClassItem
import com.dscnita.attendancetakingapp.entities.StudentItem
import com.dscnita.attendancetakingapp.entities.relations.ClassItemWithStudentItems

class AttendanceRepository(private val dao: AttendanceDao) {

    suspend fun insertClassItem(classItem: ClassItem) = dao.insertClassItem(classItem)

    suspend fun insertStudentItem(studentItem: StudentItem) = dao.insertStudentItem(studentItem)

    suspend fun updateStudentItem(studentItem: StudentItem) = dao.updateStudentItem(studentItem)

    val allClassItems: LiveData<List<ClassItem>> = dao.getClassItems()

    fun getClassItemWithStudentItems(c_id: Int): LiveData<List<ClassItemWithStudentItems>> = dao.getClassItemWithStudentItems(c_id)

    suspend fun deleteClassItem(classItem: ClassItem) = dao.deleteClassItem(classItem)

    suspend fun deleteStudentItem(studentItem: StudentItem) = dao.deleteStudentItem(studentItem)
}