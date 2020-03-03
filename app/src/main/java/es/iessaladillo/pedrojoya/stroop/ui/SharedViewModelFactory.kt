package es.iessaladillo.pedrojoya.stroop.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.stroop.room.entities.LocalRepository

class SharedViewModelFactory(private val localRepository: LocalRepository,
                                        private val application: Application) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SharedViewModel(localRepository, application) as T
    }

}