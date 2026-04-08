package com.example.studentproject.view

import android.R
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.RequestQueue
import com.example.studentproject.model.Student
import com.android.volley.toolbox.Volley

class DetailViewModel: ViewModel() {

    val studentLD = MutableLiveData<Student>()
    val errorLD = MutableLiveData<Boolean>(0)
    val TAG:String = "Volley Tag"
    var queue: RequestQueue?= null
    fun fetch(id : String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.jsonkeeper.com/b/LLMW"

        val stringRequest(Request.Method.GET, url, {
            val sType = object: TypeToken<Student>() {}.type
            val result = Gson().fromJson<List<Student>>(it, sType) as ArrayList
            val student = result.find {it.id == id} as Student
            studentLD.value = student
        },{
            Log.d("volley_status", it.message.toString())
            studentLD.value = true
        })
        stringRequest.tag = TAG
        queue.add(stringRequest)


    }

    private fun getApplication() {
        TODO("Not yet implemented")
    }


}