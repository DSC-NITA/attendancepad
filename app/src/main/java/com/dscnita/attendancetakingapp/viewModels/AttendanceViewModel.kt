package com.dscnita.attendancetakingapp.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.dscnita.attendancetakingapp.database.AttendanceDatabase
import com.dscnita.attendancetakingapp.entities.ClassItem
import com.dscnita.attendancetakingapp.entities.StudentItem
import com.dscnita.attendancetakingapp.entities.relations.ClassItemWithStudentItems
import com.dscnita.attendancetakingapp.repository.AttendanceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AttendanceViewModel(application: Application) : AndroidViewModel(application) {

    val allClassItems: LiveData<List<ClassItem>>
    private val repository: AttendanceRepository
    init {
        val dao = AttendanceDatabase.getDatabase(application).getDao()
        repository = AttendanceRepository(dao)
        allClassItems = repository.allClassItems
    }

    fun insertClassItem(classItem: ClassItem) = viewModelScope.launch(Dispatchers.IO){
        repository.insertClassItem(classItem)
    }

    fun insertStudentItem(studentItem: StudentItem) = viewModelScope.launch(Dispatchers.IO){
        repository.insertStudentItem(studentItem)
    }

    fun updateStudentItem(studentItem: StudentItem) = viewModelScope.launch(Dispatchers.IO){
        repository.updateStudentItem(studentItem)
    }

    fun getClassItemWithStudentItems(c_id: Int): LiveData<List<ClassItemWithStudentItems>> = repository.getClassItemWithStudentItems(c_id)
}