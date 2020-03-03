package es.iessaladillo.pedrojoya.stroop.room.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.iessaladillo.pedrojoya.stroop.room.entities.dao.PlayerDao

@Database(entities = [Player::class], version = 1, exportSchema = true)
abstract class StroopDatabase : RoomDatabase() {

    abstract val playerDao: PlayerDao

    companion object {
        
        @Volatile
        private var INSTANCE: StroopDatabase? = null

        fun getInstance(context: Context): StroopDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            StroopDatabase::class.java,
                            "stroop_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }

    }

}