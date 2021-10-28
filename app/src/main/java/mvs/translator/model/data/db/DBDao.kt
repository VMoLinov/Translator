package mvs.translator.model.data.db

import androidx.room.*

@Dao
interface DBDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDataModel(data: List<RoomDataModel>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMeaning(data: List<RoomMeaning>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTranslation(data: List<RoomTranslation>)

    @Update
    fun update(data: List<RoomDataModel>)

    @Delete
    fun delete(data: List<RoomDataModel>)

    @Transaction
    fun insertData(
        roomDataModel: List<RoomDataModel>,
        roomMeaning: List<RoomMeaning>,
        roomTranslation: List<RoomTranslation>
    ) {
        insertDataModel(roomDataModel)
        insertMeaning(roomMeaning)
        insertTranslation(roomTranslation)
    }

    @Query("SELECT * FROM RoomTranslation WHERE word = :word")
    fun getTranslationByWord(word: String): List<RoomTranslation>

    @Query("SELECT * FROM RoomMeaning WHERE word = :word")
    fun getMeaningByWord(word: String): List<RoomMeaning>

    @Query("SELECT * FROM RoomDataModel WHERE word = :word")
    fun getDataModelByWord(word: String): List<RoomDataModel>
}
