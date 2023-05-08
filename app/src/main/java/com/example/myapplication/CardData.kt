package com.example.myapplication

data class CardData(
    val name: String,
    val email: String,
) {
    constructor() : this("", "")
}
