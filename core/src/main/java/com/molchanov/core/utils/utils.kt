package com.molchanov.core.utils

fun findWordInText(text: String, find: String): Boolean {

    if (text.length < find.length) return false
    if (find.isBlank()) return false

    return Regex(find, RegexOption.IGNORE_CASE).findAll(text).toList().isNotEmpty()
}