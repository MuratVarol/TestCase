package com.varol.testcase.internal.extension

import com.varol.testcase.internal.util.Constants
import java.text.SimpleDateFormat
import java.util.*

val Long.Companion.ZERO: Long
    get() = 0L


fun Long?.toFormattedDate(): String? {
    if (this == null)
        return null
    val format =
        SimpleDateFormat(Constants.Date.DATE_FORMAT_SHORT, Locale.getDefault())
    return format.format(this.toDate())
}


fun Long?.toDate(): Date {
    if (this == null)
        return Calendar.getInstance().time
    return Date(this)
}
