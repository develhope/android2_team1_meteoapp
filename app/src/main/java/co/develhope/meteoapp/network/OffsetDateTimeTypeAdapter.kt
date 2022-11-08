package co.develhope.meteoapp.network

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeFormatterBuilder
import java.lang.reflect.Type



class OffsetDateTimeTypeAdapter : JsonSerializer<OffsetDateTime>, JsonDeserializer<OffsetDateTime> {

    override fun serialize(
        src: OffsetDateTime,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement = JsonPrimitive(singleFormatter.format(src))

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): OffsetDateTime = singleFormatter.parse(json.asString, OffsetDateTime.FROM)

    companion object {
        private val FORMATTER = DateTimeFormatter.ISO_DATE

        private val singleFormatter: DateTimeFormatter = DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ofPattern("YYYY-MM-ddHHmm"))
            .append(DateTimeFormatter.ISO_DATE)
            .toFormatter()

    }
}