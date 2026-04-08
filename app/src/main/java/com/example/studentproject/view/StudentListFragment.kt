package com.example.studentproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentproject.R
import com.example.studentproject.databinding.FragmentStudentDetailBinding
import com.example.studentproject.databinding.FragmentStudentListBinding
import com.example.studentproject.model.Student
import com.example.studentproject.viewmodel.ListViewModel



class StudentListFragment : Fragment() {
    private lateinit var binding: FragmentStudentListBinding
    private val studentListAdapter = StudentListAdapter.StudentListAdapter(arrayListOf())
    private lateinit var viewModel: ListViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh() // untuk mengisi arrayList

        binding.recViewStudent.layoutManager = LinearLayoutManager(context)
        binding.recViewStudent.adapter = studentListAdapter

        // swipe refresh handle
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
        observeViewModel()

    }

    fun observeViewModel(){
        // Observe studentsLD
        viewModel.studentsLD.observe(viewLifecycleOwner, Observer {
            studentListAdapter.updateStudentList(it)
        })

        // Observe errorLD
        viewModel.errorLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                binding.txtError.visibility = View.VISIBLE
            }else{
                binding.txtError.visibility = View.GONE
            }
        })

        // Observe progressLD
        viewModel.errorLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                binding.progressLoad.visibility = View.VISIBLE
                binding.recViewStudent.visibility = View.GONE
            }else{
                binding.progressLoad.visibility = View.GONE
                binding.recViewStudent.visibility = View.VISIBLE
            }
        })




    }

}


