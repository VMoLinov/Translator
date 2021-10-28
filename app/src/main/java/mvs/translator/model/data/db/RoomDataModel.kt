package mvs.translator.model.data.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class RoomDataModel(
    @PrimaryKey val text: String,
    val word: String
)

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomDataModel::class,
        parentColumns = ["text"],
        childColumns = ["parentText"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomMeaning(
    @PrimaryKey
    val imageUrl: String,
    val parentText: String
)

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomDataModel::class,
        parentColumns = ["text"],
        childColumns = ["parentText"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomTranslation(
    @PrimaryKey
    val translation: String,
    val parentText: String
)
