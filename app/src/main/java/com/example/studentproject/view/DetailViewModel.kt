package com.example.studentproject.view

import android.R
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.RequestQueue
import com.example.studentproject.model.Student
import com.android.volley.toolbox.Volley

class DetailViewModel: ViewModel() {

    val studentLD = MutableLiveData<Student>()
    val TAG:String = "Volley Tag"
    var queue: RequestQueue?= null
    fun fetch(student : Student) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.jsonkeeper.com/b/LLMW"

        val stringRequest(Request.Method.GET, url, {},{})
        stringRequest.tag = TAG
        queue.add(stringRequest)

        studentLD.value = student
    }

    private fun getApplication() {
        TODO("Not yet implemented")
    }


}