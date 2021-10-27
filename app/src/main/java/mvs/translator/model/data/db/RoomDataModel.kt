package mvs.translator.model.data.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class RoomDataModel(
    @PrimaryKey val text: String?
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
    val imageUrl: String?,
    val parentText: String?
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
    val translation: String?,
    val parentText: String?
)
