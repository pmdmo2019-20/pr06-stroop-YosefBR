package es.iessaladillo.pedrojoya.stroop.ui.playerFragment

import android.app.Application
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.stroop.room.entities.Player
import es.iessaladillo.pedrojoya.stroop.room.entities.Repository

class NewPlayerFragmentViewModel(private val repository: Repository,
                                 private val application: Application) : ViewModel() {

    var avatar: Int  = -1

    fun addPlayer(playerName: String) {
        Thread {
            try {
                repository.addPlayer(Player(0, playerName, avatar))
            } catch (e: Exception) {

            }
        }.start()
    }

}