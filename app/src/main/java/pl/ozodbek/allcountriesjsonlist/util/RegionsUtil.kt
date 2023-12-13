package pl.ozodbek.allcountriesjsonlist.util

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import kotlinx.serialization.json.Json
import pl.ozodbek.allcountriesjsonlist.data.CountriesResponse
import pl.ozodbek.allcountriesjsonlist.data.Country
import java.io.IOException

class RegionsUtil {
    private val json = Json { ignoreUnknownKeys = true }

    fun getCountryCode(context: Context): CountriesResponse {
        val jsonString: String = try {
            context.assets.open("countries.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            Log.d(TAG, "getCountryCode: Exception $ioException")
            ""
        }

        return json.decodeFromString(jsonString)
    }
}
