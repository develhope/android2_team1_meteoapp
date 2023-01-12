package co.develhope.meteoapp.network

import co.develhope.meteoapp.ForecastInfoObject
import co.develhope.meteoapp.dto.GeocodingDTO
import org.intellij.lang.annotations.Language
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingApiService {
    @GET("v1/search?")
    suspend fun getLocation(
        @Query("name") name: String? = null,
        @Query("language") language: String = ForecastInfoObject.changeGeocodingSearchLanguage()
        //@Query("count") count: Int = 5
    ): Response<GeocodingDTO>
}