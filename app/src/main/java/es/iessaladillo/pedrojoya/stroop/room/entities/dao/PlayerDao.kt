package es.iessaladillo.pedrojoya.stroop.room.entities.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import es.iessaladillo.pedrojoya.stroop.room.entities.Player

@Dao
interface PlayerDao {

    @Query("SELECT * FROM PLAYER")
    fun queryAllPlayers(): LiveData<List<Player>>

    @Insert
    fun insertStudent(player: Player)

    @Update
    fun updateStudent(player: Player): Int

    @Delete
    fun deleteStudent(player: Player): Int

}