package ru.music.discussions

import kotlin.reflect.KClass

class MpLoggerProvider(
    private val provider: (String) -> IMpLogWrapper = { IMpLogWrapper.DEFAULT }
) {
    fun logger(loggerId: String) = provider(loggerId)
    fun logger(clazz: KClass<*>) = provider(clazz.qualifiedName ?: clazz.simpleName ?: "(unknown)")
}