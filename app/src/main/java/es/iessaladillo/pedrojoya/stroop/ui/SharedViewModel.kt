package es.iessaladillo.pedrojoya.stroop.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.stroop.room.entities.Game
import es.iessaladillo.pedrojoya.stroop.room.entities.Player
import es.iessaladillo.pedrojoya.stroop.room.entities.Repository

class SharedViewModel(private val repository: Repository,
                      private val application: Application) : ViewModel() {

    val players: LiveData<List<Player>> = repository.queryAllPlayers()
    val games: LiveData<List<Game>> = repository.queryAllGames()
    var actualPlayer: MutableLiveData<Player>? = null

    var gameMode: String = ""
    var minutes: Int = 0
    var points: Int = 0
    var words: Int = 0
    var correct: Int = 0

    fun addGame() {
        Thread {
            try {
                repository.addGame(Game(0, actualPlayer?.value?.id!!, gameMode, correct, minutes, points, words))
            } catch (e: Exception) {

            }
        }.start()
    }

}