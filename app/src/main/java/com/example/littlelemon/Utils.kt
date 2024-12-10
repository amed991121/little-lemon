package com.example.littlelemon

import android.app.Activity
import android.widget.Toast

fun Activity.toast(message: Message) {
    when (message) {
        is Message.DynamicString ->
            Toast.makeText(this, message.value, Toast.LENGTH_LONG).show()
        is Message.StringResource ->
            Toast.makeText(this, message.resId, Toast.LENGTH_LONG).show()
    }

}