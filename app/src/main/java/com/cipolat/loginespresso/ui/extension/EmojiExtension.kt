package com.cipolat.loginespresso.ui.extension

fun Int.getEmojiByUnicode(): String {
    return String(Character.toChars(this))
}