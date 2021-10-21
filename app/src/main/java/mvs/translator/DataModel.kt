package mvs.translator

import com.google.gson.annotations.SerializedName

/** Модель данных */
data class DataModel(
    val text: String?,
    @SerializedName("meanings")
    val meaning: List<Meaning>?
)

/** Значение слова/фразы */
data class Meaning(
    val translation: Translation?,
    val imageUrl: String?
)

/** Перевод слова/фразы */
data class Translation(
    @SerializedName("text")
    val translation: String?
)
