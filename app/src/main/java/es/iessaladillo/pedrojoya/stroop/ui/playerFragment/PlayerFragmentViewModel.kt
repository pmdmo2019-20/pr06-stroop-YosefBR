package es.iessaladillo.pedrojoya.stroop.ui.playerFragment

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.stroop.room.entities.Player
import es.iessaladillo.pedrojoya.stroop.room.entities.Repository

class PlayerFragmentViewModel(private val repository: Repository,
                              private val application: Application) : ViewModel() {

    val players: LiveData<List<Player>> = repository.queryAllPlayers()
}