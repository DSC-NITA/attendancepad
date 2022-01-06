package com.dscnita.attendancetakingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.dscnita.attendancetakingapp.databinding.ActivityMainBinding
import com.dscnita.attendancetakingapp.entities.ClassItem
import com.dscnita.attendancetakingapp.entities.StudentItem
import com.dscnita.attendancetakingapp.entities.relations.ClassItemWithStudentItems
import com.dscnita.attendancetakingapp.viewModels.AttendanceViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    lateinit var viewModel: AttendanceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

//        ----------------------------------------- EXAMPLES ----------------------------------------
//        // 1. initialise viewmodel
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[AttendanceViewModel::class.java]
//
//        // 2. observing livedata<ClassItem> & updating adapter
//        viewModel.allClassItems.observe(this,Observer {
//            it?.let {
//                 //adapterClass.updateClassItems(it)
////                Toast.makeText(this,"Android very bad",Toast.LENGTH_SHORT).show()
//            }
//        })
//
//        // 3. insert in ClassItem
//        viewModel.insertClassItem(ClassItem("10B", "Maths"))
//
//        // 4. getting (livedata) class with students with c_id
//        val classItemWithStudentItem: LiveData<List<ClassItemWithStudentItems>> = viewModel.getClassItemWithStudentItems(5)
//
//        // 5. observing livedata<ClassItemWithStudentItems> followed by livedata<StudentItem>, & updating adapter
//        // WARNING : I don't know whether nested livedata work or not, yet to test
//        classItemWithStudentItem.observe(this, Observer {
//            it?.let {
//                val studentItems: LiveData<List<StudentItem>> = it[0].studentItems
//                studentItems.observe(this, Observer { studentList ->
//                    // adapterStudent.updateStudentItems(studentList)
//                })
//            }
//        })
//
//        // 6. insert in StudentItem
//        viewModel.insertStudentItem(StudentItem(5, "27", "Akshay", true))
//
//        // 7. Update in an instance of StudentItem
//        viewModel.updateStudentItem(3, false)
    }
}