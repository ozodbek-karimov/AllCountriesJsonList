package pl.ozodbek.allcountriesjsonlist.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class CountriesResponse(
    @SerialName("Countries") val countries: List<Country>
): Parcelable