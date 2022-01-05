package com.dscnita.attendancetakingapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dscnita.attendancetakingapp.entities.ClassItem
import com.dscnita.attendancetakingapp.entities.StudentItem
import com.dscnita.attendancetakingapp.entities.relations.ClassItemWithStudentItems

@Dao
interface AttendanceDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertClassItem(classItem: ClassItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudentItem(studentItem: StudentItem)

    @Query("UPDATE StudentItem SET status = :status WHERE s_id = :s_id")
    suspend fun updateStudentItem(s_id: Int, status: Boolean)

    @Query("SELECT * FROM ClassItem ORDER BY c_id ASC")
    fun getClassItems(): LiveData<List<ClassItem>>

    @Transaction
    @Query("SELECT * FROM ClassItem where c_id = :c_id")
    fun getClassItemWithStudentItems(c_id: Int): LiveData<List<ClassItemWithStudentItems>>
}