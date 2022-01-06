package com.dscnita.attendancetakingapp.repository

import androidx.lifecycle.LiveData
import com.dscnita.attendancetakingapp.dao.AttendanceDao
import com.dscnita.attendancetakingapp.entities.ClassItem
import com.dscnita.attendancetakingapp.entities.StudentItem
import com.dscnita.attendancetakingapp.entities.relations.ClassItemWithStudentItems

class AttendanceRepository(private val dao: AttendanceDao) {

    suspend fun insertClassItem(classItem: ClassItem) = dao.insertClassItem(classItem)

    suspend fun insertStudentItem(studentItem: StudentItem) = dao.insertStudentItem(studentItem)

    suspend fun updateStudentItem(s_id: Int, status: Boolean) = dao.updateStudentItem(s_id, status)

    val allClassItems: LiveData<List<ClassItem>> = dao.getClassItems()

    fun getClassItemWithStudentItems(c_id: Int): LiveData<List<ClassItemWithStudentItems>> = dao.getClassItemWithStudentItems(c_id)
}