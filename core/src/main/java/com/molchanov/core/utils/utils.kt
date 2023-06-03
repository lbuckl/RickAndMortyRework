package com.molchanov.core.utils

/**
 * Функция ищет слово в строке возвращает true, если найдено
 * @param text - текст для поиска
 * @param find - слово для поиска
 */
fun findWordInText(text: String, find: String): Boolean {

    if (text.length < find.length) return false
    if (find.isBlank()) return false

    return Regex(find, RegexOption.IGNORE_CASE).findAll(text).toList().isNotEmpty()
}