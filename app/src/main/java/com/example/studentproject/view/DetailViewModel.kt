package com.example.studentproject.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studentproject.model.Student

class DetailViewModel: ViewModel() {

    val studentLD = MutableLiveData<Student>()
    fun fetch(student : Student) {

        studentLD.value = student
    }



}