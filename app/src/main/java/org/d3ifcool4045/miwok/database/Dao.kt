package org.d3ifcool4045.miwok.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MiwokDao {

    @Query("SELECT * FROM miwok")
    fun getMiwok(): LiveData<List<MiwokDatabaseModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(miwok: List<MiwokDatabaseModel>)

}

@Database(entities = [MiwokDatabaseModel::class], version = 1, exportSchema = false)
abstract class MiwokDatabase : RoomDatabase() {
    abstract val Dao: MiwokDao
}

private lateinit var INSTANCE: MiwokDatabase

fun getInstance(context: Context) : MiwokDatabase {
    synchronized(MiwokDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                MiwokDatabase::class.java,
                "miwok_database").build()
        }
    }
    return INSTANCE
}

