package com.dscnita.attendancetakingapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dscnita.attendancetakingapp.R
import com.dscnita.attendancetakingapp.adapters.StudentAdapter
import com.dscnita.attendancetakingapp.databinding.FragmentStudentBinding
import com.dscnita.attendancetakingapp.entities.StudentItem

class StudentFragment : Fragment() {
    private var _binding: FragmentStudentBinding?=null
    private val binding get()= _binding!!
    private val studentItems = mutableListOf<StudentItem>()

    private lateinit var className:String
    private lateinit var subjectName:String

    companion object {
        val CLASSNAME = "className"
        val SUBJECTNAME="subjectName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            className=it.getString(CLASSNAME).toString()
            subjectName=it.getString(SUBJECTNAME).toString()
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
        recyclerView.adapter= StudentAdapter(requireContext(),studentItems,itemOnClick)
        setToolBar(className,subjectName)
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
        studentItems.add(StudentItem(rollNo,studentName))
        binding.recyclerView.adapter?.notifyItemInserted(studentItems.size-1)
    }


    private val itemOnClick: (View, Int, Int) -> Unit = { _, position, _ ->
        val status=studentItems[position].status
        if(status=="P")
            studentItems[position].status="A"
        else
            studentItems[position].status="P"
        binding.recyclerView.adapter?.notifyItemChanged(position)
    }

}