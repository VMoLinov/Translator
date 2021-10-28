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

    @Query("SELECT * FROM RoomTranslation WHERE parentText = :parentText")
    fun getTranslationByParentText(parentText: String): List<RoomTranslation>

    @Query("SELECT * FROM RoomMeaning WHERE parentText = :parentText")
    fun getMeaningByParentText(parentText: String): List<RoomMeaning>

    @Query("SELECT * FROM RoomDataModel WHERE text = :parentText")
    fun getDataModelByParentText(parentText: String): List<RoomDataModel>
}
