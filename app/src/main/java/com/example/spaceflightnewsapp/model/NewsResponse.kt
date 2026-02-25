package com.example.spaceflightnewsapp.model

data class NewsResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)