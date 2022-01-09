package com.dscnita.attendancetakingapp.fragments

import android.app.Application
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dscnita.attendancetakingapp.R
import com.dscnita.attendancetakingapp.adapters.ClassAdapter
import com.dscnita.attendancetakingapp.databinding.FragmentClassBinding
import com.dscnita.attendancetakingapp.entities.ClassItem
import com.dscnita.attendancetakingapp.viewModels.AttendanceViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.Observer

class ClassFragment : Fragment() {
    private var _binding: FragmentClassBinding?=null
    private val binding get()= _binding!!
    private val classItems = mutableListOf<ClassItem>()
    lateinit var viewModel: AttendanceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClassBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.fabMain.setOnClickListener {
            showDialogBox()
        }

        val recyclerView=binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        val adapter=ClassAdapter(requireContext(),classItems)
        recyclerView.adapter= adapter

        adapter.onItemClick={_,position->
            val action= ClassFragmentDirections.actionClassFragmentToStudentFragment(
                className = classItems[position].className,
                subjectName = classItems[position].subjectName,
                classId = classItems[position].c_id)
            findNavController().navigate(action)
        }

        adapter.onItemLongClick={_,position->
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Delete Class")
                .setMessage("Are you sure you want to delete entry of ${classItems[position].className}?")
                .setNegativeButton("Cancel"){
                        dialog,_->
                    dialog.dismiss()
                }
                .setPositiveButton("Delete"){
                        dialog,_->
                    viewModel.deleteClassItem(classItems[position])
                    dialog.dismiss()
                }
                .show()
        }

        setToolBar()

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(Application()))[AttendanceViewModel::class.java]
        viewModel.allClassItems.observe(viewLifecycleOwner, {
            it?.let {
                 adapter.updateClassItems(it)
            }
        })

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setToolBar()
    {
        val toolbar=binding.toolbars
        toolbar.toolbarTitle.text="Attendance Pad"
        toolbar.toolbarSubtitle.visibility=View.GONE
        toolbar.backButton.visibility=View.INVISIBLE
        toolbar.menuButton.visibility=View.GONE
    }

    private fun showDialogBox()
    {
        val builder= AlertDialog.Builder(requireContext())
        val view=LayoutInflater.from(requireContext()).inflate(R.layout.class_dialog,null)
        builder.setView(view)
        val dialog=builder.create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()


        val cancelButton=view.findViewById<Button>(R.id.cancel_button)
        val addButton=view.findViewById<Button>(R.id.add_button)

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        addButton.setOnClickListener {
            addClass(view)
            dialog.dismiss()
        }
    }

    private fun addClass(view: View)
    {
        val className=view.findViewById<EditText>(R.id.className).text.toString()
        val subjectName=view.findViewById<EditText>(R.id.subjectName).text.toString()
        viewModel.insertClassItem(ClassItem(className,subjectName))
//        binding.recyclerView.adapter?.notifyItemInserted(classItems.size-1)
    }
}