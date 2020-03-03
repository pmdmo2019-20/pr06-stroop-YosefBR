package es.iessaladillo.pedrojoya.stroop.room.entities

import androidx.lifecycle.LiveData

interface Repository {

    //Player
    fun queryAllPlayers(): LiveData<List<Player>>
    fun addPlayer(player: Player)
    fun updatePlayer(player: Player): Int
    fun deletePlayer(player: Player): Int

    //Player
    fun queryAllGames(): LiveData<List<Game>>
    fun addGame(game: Game)
}