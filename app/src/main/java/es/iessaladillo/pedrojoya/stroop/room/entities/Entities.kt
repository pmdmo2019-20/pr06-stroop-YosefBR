package es.iessaladillo.pedrojoya.stroop.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "player")
data class Player (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "avatar")
    val avatar: Int
)


@Entity(
    tableName = "game",
    foreignKeys = [
        ForeignKey(
            entity = Player::class,
            parentColumns = ["id"],
            childColumns = ["playerID"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        )
    ]
)
data class Game (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "playerID")
    val playerID: Long,
    @ColumnInfo(name = "gameMode")
    val gameMode: String,
    @ColumnInfo(name = "correct")
    val correct: Int,
    @ColumnInfo(name = "minutes")
    val minutes: Int,
    @ColumnInfo(name = "points")
    val points: Int,
    @ColumnInfo(name = "words")
    val words: Int
)