package org.d3ifcool4045.miwok.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.d3ifcool4045.miwok.data.Miwok

@Entity(tableName = "miwok")
data class MiwokDatabaseModel constructor(
    val category: String,
    val background: String,
    val defaultWord: String,
    @PrimaryKey
    val miwokWord: String,
    val image: String = ""
)

fun List<MiwokDatabaseModel>.asDomainModel(): List<Miwok> {
    return map {
        Miwok(
            category = it.category,
            background = it.background,
            defaultWord = it.defaultWord,
            miwokWord = it.miwokWord,
            image = it.image
        )
    }
}

fun List<Miwok>.asDatabaseModel(): List<MiwokDatabaseModel> {
    return map {
        MiwokDatabaseModel (
            category = it.category,
            background = it.background,
            defaultWord = it.defaultWord,
            miwokWord = it.miwokWord,
            image = it.image
        )
    }
}