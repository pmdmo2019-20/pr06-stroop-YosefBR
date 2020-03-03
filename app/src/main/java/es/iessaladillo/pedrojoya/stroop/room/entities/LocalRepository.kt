package es.iessaladillo.pedrojoya.stroop.room.entities

import androidx.lifecycle.LiveData
import es.iessaladillo.pedrojoya.stroop.room.entities.dao.GameDao
import es.iessaladillo.pedrojoya.stroop.room.entities.dao.PlayerDao

class LocalRepository(private val playerDao: PlayerDao, private val gameDao: GameDao) : Repository{

    override fun queryAllGames(): LiveData<List<Game>> = gameDao.queryAllGames()

    override fun addGame(game: Game) = gameDao.insertGame(game)

    override fun queryAllPlayers(): LiveData<List<Player>> = playerDao.queryAllPlayers()

    override fun addPlayer(player: Player) = playerDao.insertStudent(player)

    override fun updatePlayer(player: Player): Int = playerDao.updateStudent(player)

    override fun deletePlayer(player: Player): Int = playerDao.deleteStudent(player)

}