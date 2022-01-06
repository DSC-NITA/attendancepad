package com.dscnita.attendancetakingapp.fragments

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dscnita.attendancetakingapp.R
import com.dscnita.attendancetakingapp.adapters.StudentAdapter
import com.dscnita.attendancetakingapp.databinding.FragmentStudentBinding
import com.dscnita.attendancetakingapp.entities.StudentItem
import com.dscnita.attendancetakingapp.entities.relations.ClassItemWithStudentItems
import com.dscnita.attendancetakingapp.viewModels.AttendanceViewModel

class StudentFragment : Fragment() {
    private var _binding: FragmentStudentBinding?=null
    private val binding get()= _binding!!
    private val studentItems = mutableListOf<StudentItem>()
    lateinit var viewModel: AttendanceViewModel

    private lateinit var className:String
    private lateinit var subjectName:String
    private var c_id=0

    companion object {
        val CLASSNAME = "className"
        val SUBJECTNAME="subjectName"
        val CLASSID="classId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            className=it.getString(CLASSNAME).toString()
            subjectName=it.getString(SUBJECTNAME).toString()
            c_id=it.getInt(CLASSID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerView=binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        val adapter=StudentAdapter(requireContext(),studentItems,itemOnClick)
        recyclerView.adapter= adapter
        setToolBar(className,subjectName)


        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(Application()))[AttendanceViewModel::class.java]

        val classItemWithStudentItem: LiveData<List<ClassItemWithStudentItems>> = viewModel.getClassItemWithStudentItems(c_id)

        classItemWithStudentItem.observe(viewLifecycleOwner,  {
            it?.let {
                val studentItems: List<StudentItem> = it[0].studentItems
                adapter.updateStudentItems(studentItems)
            }
        })
    }

    private fun setToolBar(className:String, subjectName:String)
    {
        val toolbar=binding.toolbar
        toolbar.toolbarTitle.text=className
        toolbar.toolbarSubtitle.text=subjectName

        toolbar.backButton.setOnClickListener {
            requireActivity()
                .onBackPressedDispatcher
                .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        // we'll add directions to class fragment later
                        val action=StudentFragmentDirections.actionStudentFragmentToClassFragment()
                        findNavController().navigate(action)
                    }
                }
                )
        }

        toolbar.menuButton.setOnClickListener {
            showAddStudentDialog()
        }

    }

    private fun showAddStudentDialog() {
        val builder= AlertDialog.Builder(requireContext())
        val view= LayoutInflater.from(requireContext()).inflate(R.layout.student_dialog,null)
        builder.setView(view)
        val dialog=builder.create()
        dialog.show()

        val cancelButton=view.findViewById<Button>(R.id.cancel_button)
        val addButton=view.findViewById<Button>(R.id.add_button)

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        addButton.setOnClickListener {
            addStudent(view)
            dialog.dismiss()
        }
    }

    private fun addStudent(view:View)
    {
        val rollNo=view.findViewById<EditText>(R.id.rollNo).text.toString()
        val studentName=view.findViewById<EditText>(R.id.studentName).text.toString()
        viewModel.insertStudentItem(StudentItem(c_id,rollNo,studentName))
    }


    private val itemOnClick: (View, Int, Int) -> Unit = { _, position, _ ->
        val s_id=studentItems[position].s_id
        val status=studentItems[position].status
        studentItems[position].status = !status
        viewModel.updateStudentItem(studentItems[position])
//        binding.recyclerView.adapter?.notifyItemChanged(position)
    }

}