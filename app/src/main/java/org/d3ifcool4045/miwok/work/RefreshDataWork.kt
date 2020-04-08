package org.d3ifcool4045.miwok.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.d3ifcool4045.miwok.database.getInstance
import org.d3ifcool4045.miwok.repository.MiwokRepository
import retrofit2.HttpException

class RefreshDataWork(
    appContext: Context, params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getInstance(applicationContext)
        val repository = MiwokRepository(database)

        return try {
            repository.refreshMiwok()
            Result.success()
        } catch (execption: HttpException) {
            Result.retry()
        }
    }
}