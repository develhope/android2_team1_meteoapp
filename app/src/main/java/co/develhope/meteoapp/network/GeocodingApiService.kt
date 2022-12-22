package co.develhope.meteoapp.network

import co.develhope.meteoapp.dto.GeocodingDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingApiService {
    @GET("v1/search?")
    suspend fun getLocation(
        @Query("name") name: String = "Roccapalumba"
    ): Response<GeocodingDTO>
}