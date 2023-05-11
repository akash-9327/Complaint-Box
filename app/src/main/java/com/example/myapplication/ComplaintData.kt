package com.example.myapplication

data class ComplaintData(
    val title: String? = null,
    val desc: String? = null,
    val eno: String? = null,
    val name: String? = null,
    val status: String? = "Pending",
    val dep:String?=null,
    val email:String?=null,
    val mobile:Number?=null,
)
