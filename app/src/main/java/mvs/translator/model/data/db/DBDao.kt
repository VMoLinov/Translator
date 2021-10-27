package mvs.translator.model.data.db

import androidx.room.*

@Dao
interface DBDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data: List<RoomDataModel>): Long

    @Update
    fun update(data: List<RoomDataModel>)

    @Delete
    fun delete(data: List<RoomDataModel>)

    @Query("SELECT * FROM RoomTranslation WHERE parentText = :parentText")
    fun getTranslationByParentText(parentText: String): RoomTranslation

    @Query("SELECT * FROM RoomMeaning WHERE parentText = :parentText")
    fun getMeaningByParentText(parentText: String): RoomMeaning

    @Query("SELECT * FROM RoomDataModel WHERE text = :parentText")
    fun getDataModelByParentText(parentText: String): List<RoomDataModel>
}
