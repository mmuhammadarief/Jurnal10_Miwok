package org.d3ifcool4045.miwok.data


import com.squareup.moshi.JsonClass

@Suppress("SpellCheckingInspection")
@JsonClass(generateAdapter = true)
data class Miwok (
    val category: String,
    val background: String,
    val defaultWord: String,
    val miwokWord: String,
    val image: String = ""
)