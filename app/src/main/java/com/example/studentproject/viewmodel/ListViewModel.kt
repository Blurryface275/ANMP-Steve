package com.example.studentproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.studentproject.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListViewModel(application: Application) : AndroidViewModel(application) {
    val studentsLD = MutableLiveData<ArrayList<Student>>()
    val errorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        errorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.jsonkeeper.com/b/LLMW"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                // VOLLEY SUCCESS
                // Mengubah JSON String menjadi ArrayList<Student> menggunakan GSON
                val sType = object : TypeToken<ArrayList<Student>>() {}.type
                val result = Gson().fromJson<ArrayList<Student>>(response, sType)

                studentsLD.value = result
                loadingLD.value = false
                Log.d("showvoley", response.toString())
            },
            {
                // VOLLEY ERROR
                // Jika terjadi error
                errorLD.value = true
                loadingLD.value = false
                Log.e("showvoley", it.toString())
            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        // Menghentikan request jika ViewModel dihancurkan agar tidak memory leak
        queue?.cancelAll(TAG)
    }
}