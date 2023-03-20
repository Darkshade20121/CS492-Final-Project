package com.example.riot.data

data class Match(
    val gameId: Long,
    val champion: Int,
    val queue: Int,
    val timestamp: Long,
    val season: Int,
    val role: String,
    val lane: String
)