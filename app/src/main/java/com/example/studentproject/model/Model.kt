package com.example.studentproject.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Student(
    var id:String,
    @SerializedName("student_name")
    var name:String,
    @SerializedName("date_of_birth")
    var bod:String,
    var phone:String,
    @SerializedName("photo_url")
    var photoURL:String
): Serializable
