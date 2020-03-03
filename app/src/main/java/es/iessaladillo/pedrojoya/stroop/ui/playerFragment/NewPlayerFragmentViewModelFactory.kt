package es.iessaladillo.pedrojoya.stroop.ui.playerFragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.stroop.room.entities.LocalRepository


class NewPlayerFragmentViewModelFactory(private val localRepository: LocalRepository,
                                        private val application: Application) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewPlayerFragmentViewModel(localRepository, application) as T
    }

}