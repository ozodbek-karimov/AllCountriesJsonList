package pl.ozodbek.allcountriesjsonlist.data


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Country(
    @SerialName("States") val states: List<City>,
    @SerialName("CountryName") val countryName: String,
    var isSelected: Boolean = false
): Parcelable