package com.dscnita.attendancetakingapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.dscnita.attendancetakingapp.R
import com.dscnita.attendancetakingapp.adapters.ClassAdapter
import com.dscnita.attendancetakingapp.databinding.FragmentClassBinding
import com.dscnita.attendancetakingapp.models.ClassItem

class ClassFragment : Fragment() {
    private var _binding: FragmentClassBinding?=null
    private val binding get()= _binding!!
    private val classItems = mutableListOf<ClassItem>()

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
        recyclerView.adapter= ClassAdapter(requireContext(),classItems)

        setToolBar()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setToolBar()
    {
        val toolbar=binding.toolbar
        toolbar.toolbarTitle.text="Attendance Pad"
        toolbar.toolbarSubtitle.visibility=View.GONE
        toolbar.saveButton.visibility=View.INVISIBLE
        toolbar.backButton.visibility=View.INVISIBLE
        toolbar.menuButton.visibility=View.GONE
    }

    private fun showDialogBox()
    {
        val builder= AlertDialog.Builder(requireContext())
        val view=LayoutInflater.from(requireContext()).inflate(R.layout.class_dialog,null)
        builder.setView(view)
        val dialog=builder.create()
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
        classItems.add(ClassItem(className,subjectName))
        binding.recyclerView.adapter?.notifyItemInserted(classItems.size-1)
    }

}