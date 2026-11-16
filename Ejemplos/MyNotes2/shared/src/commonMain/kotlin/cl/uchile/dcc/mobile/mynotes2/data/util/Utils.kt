package cl.uchile.dcc.mobile.mynotes2.data.util

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.random.Random
import kotlin.time.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun getRandomColor(): Long {
    return Random.nextLong(0xFF000000, 0xFFFFFFFF)
}

fun Long.getLocalDateTimeFormat(): String {
    val dateTime = Instant.fromEpochMilliseconds(this)
        .toLocalDateTime(TimeZone.currentSystemDefault())

    val dd = dateTime.day.toString().padStart(2, '0')
    val mm = dateTime.monthNumber.toString().padStart(2, '0')
    val yyyy = dateTime.year.toString()
    val h = dateTime.hour.toString().padStart(2, '0')
    val m = dateTime.minute.toString().padStart(2, '0')

    return "$dd/$mm/$yyyy $h:$m"
}

enum class PictureSize(val size: Dp) {
    THUMBNAIL(40.dp),
    NORMALSIZE(320.dp)
}