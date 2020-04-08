package org.d3ifcool4045.miwok.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.d3ifcool4045.miwok.data.Miwok
import org.d3ifcool4045.miwok.database.MiwokDatabase
import org.d3ifcool4045.miwok.database.asDatabaseModel
import org.d3ifcool4045.miwok.network.MiwokApi
import retrofit2.await

class MiwokRepository(private val database: MiwokDatabase) {

    val miwok: LiveData<List<Miwok>> = Transformations.map(database.Dao.getMiwok()) {
        it.asDomainModel()
    }

    suspend fun refreshMiwok() {
        withContext(Dispatchers.IO) {
            val miwok = MiwokApi.retrofitService.showList().await()
            database.Dao.insertAll(miwok.asDatabaseModel())
        }
    }
}