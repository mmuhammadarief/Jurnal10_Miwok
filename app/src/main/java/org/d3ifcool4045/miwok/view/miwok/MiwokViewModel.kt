package org.d3ifcool4045.miwok.view.miwok

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.d3ifcool4045.miwok.database.getInstance
import org.d3ifcool4045.miwok.repository.MiwokRepository

@Suppress("SpellCheckingInspection")
class MiwokViewModel(application: Application) : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response : LiveData<String>
        get() = _response

    private var job = Job()
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    private val database = getInstance(application)
    private val miwokRepository = MiwokRepository(database)

    init {
        uiScope.launch {
            try {
                miwokRepository.refreshMiwok()
            } catch (t: Throwable){
                _response.value = "No internet connection!"
            }
        }
    }

    val miwok = miwokRepository.miwok

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MiwokViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MiwokViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}