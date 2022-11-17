package co.develhope.meteoapp.network

import co.develhope.meteoapp.data.domainmodel.CardInfo
import co.develhope.meteoapp.data.domainmodel.TodayCardInfo
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.threeten.bp.OffsetDateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkObject {

    private fun provideRetrofit(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    private fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapter(OffsetDateTime::class.java, OffsetDateTimeTypeAdapter())
        .create()


    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private val retrofit = provideRetrofit(
        client = provideOkHttpClient(loggingInterceptor = provideHttpLoggingInterceptor()),
        gson = provideGson()
    )

    private val service = retrofit.create(ForecastApiService::class.java)

    suspend fun getWeeklySummary(): List<CardInfo> {
        return service.getWeeklyForecast().body()?.dailyDTO?.toDomain() ?: emptyList()
    }

    suspend fun getHourlyForecastForASpecificDay(): List<TodayCardInfo> {
        return service.getHourlyForecastForASpecificDay().body()?.hourlyDTO?.toDomain() ?: emptyList()
    }
}