package com.example.studentproject.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.studentproject.databinding.StudentCardItemBinding
import com.example.studentproject.model.Student

// Gunakan satu class saja, jangan double
class StudentListAdapter(val studentList: ArrayList<Student>)
    : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>()
{
    // Gunakan nama binding yang konsisten (StudentCardItemBinding)
    class StudentViewHolder(var binding: StudentCardItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentCardItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        holder.binding.txtNrp.text = student.id
        holder.binding.txtName.text = student.name

        holder.binding.btnDetail.setOnClickListener {
            // Perhatikan huruf besar 'S' pada StudentListFragmentDirections
            val action = StudentListFragmentDirections.actionStudentListFragmentToStudentDetailFragment(student)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount() = studentList.size

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}