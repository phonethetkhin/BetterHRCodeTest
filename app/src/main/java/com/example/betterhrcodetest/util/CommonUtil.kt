package com.example.betterhrcodetest.util

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat

fun changeDateFormat(dateString: String): String {
    return SimpleDateFormat("dd MMMM yyyy hh:mm aaa").format(
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(
            dateString
        )!!
    )
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}