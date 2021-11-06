package mvs.translator.model.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/** Модель данных */
@Parcelize
data class DataModel(
    val text: String,
    @SerializedName("meanings")
    val meanings: List<Meanings>?
) : Parcelable

/** Значение слова/фразы */
@Parcelize
data class Meanings(
    val translation: Translation?,
    val imageUrl: String
) : Parcelable

/** Перевод слова/фразы */
@Parcelize
data class Translation(
    @SerializedName("text")
    val translation: String
) : Parcelable
