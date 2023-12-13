package pl.ozodbek.allcountriesjsonlist.data


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class City(
    @SerialName("Cities") val cities: List<String>,
    @SerialName("StateName") val stateName: String,
    var isSelected: Boolean = false
): Parcelable