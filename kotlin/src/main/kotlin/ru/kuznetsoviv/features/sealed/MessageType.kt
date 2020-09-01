package ru.kuznetsoviv.features.sealed

/**
 * Позволяет ограничить иерархию классов конкретным множеством подтипов.
 */
sealed class MessageType
class MessageSuccess(var msg: String) : MessageType()
class MessageFailure(var msg: String, var e: Exception) : MessageType()

fun main() {
    val messageSuccess = MessageSuccess("Yap!")
    val messageFailure = MessageFailure("Boo!", Exception("Gone wrong!"))
}