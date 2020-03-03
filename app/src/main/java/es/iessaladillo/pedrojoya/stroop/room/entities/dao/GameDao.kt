package es.iessaladillo.pedrojoya.stroop.room.entities.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.iessaladillo.pedrojoya.stroop.room.entities.Game
import es.iessaladillo.pedrojoya.stroop.room.entities.Player

@Dao
interface GameDao {

    @Query("SELECT * FROM GAME")
    fun queryAllGames(): LiveData<List<Game>>

    @Insert
    fun insertGame(game: Game)
}