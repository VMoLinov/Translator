package mvs.translator.model.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/** Модель данных */
@Parcelize
data class DataModel(
    val text: String?,
    @SerializedName("meanings")
    val meaning: List<Meaning>?
) : Parcelable

/** Значение слова/фразы */
@Parcelize
data class Meaning(
    val translation: Translation?,
    val imageUrl: String?
) : Parcelable

/** Перевод слова/фразы */
@Parcelize
data class Translation(
    @SerializedName("text")
    val translation: String?
) : Parcelable
