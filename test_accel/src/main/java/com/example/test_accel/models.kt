package com.example.test_accel

data class Info(
    val author: String = "undefined",
    val address: String = "undefined",
    val description: String = "undefined",
): java.io.Serializable
data class MapModel(
    val name: String = "",
    val preview: String = "",
    val info: Info = Info(),
    val image: String = ""
): java.io.Serializable
data class ServerResponse(
    val count: Int = 0,
    val results: List<MapModel> = listOf()
)