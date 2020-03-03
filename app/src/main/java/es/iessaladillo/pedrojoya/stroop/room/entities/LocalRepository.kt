package es.iessaladillo.pedrojoya.stroop.room.entities

import androidx.lifecycle.LiveData
import es.iessaladillo.pedrojoya.stroop.room.entities.dao.PlayerDao

class LocalRepository(private val playerDao: PlayerDao) : Repository{

    override fun queryAllPlayers(): LiveData<List<Player>> = playerDao.queryAllPlayers()

    override fun addPlayer(player: Player) = playerDao.insertStudent(player)

    override fun updatePlayer(player: Player): Int = playerDao.updateStudent(player)

    override fun deletePlayer(player: Player): Int = playerDao.deleteStudent(player)

}