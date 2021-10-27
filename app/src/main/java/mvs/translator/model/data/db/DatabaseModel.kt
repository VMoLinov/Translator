package mvs.translator.model.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [RoomDataModel::class, RoomMeaning::class, RoomTranslation::class],
    version = 1
)
abstract class DatabaseModel : RoomDatabase() {

    abstract val dbDao: DBDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: DatabaseModel? = null

        fun getInstance() = instance ?: throw IllegalStateException("Database not initialized")

        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, DatabaseModel::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}
