package ru.kuznetsoviv.features.oop.api

interface Roamable {

    // В интерфейсе можно добавить только абстракное свойство
    // В get и set-методах нельзя использовать field, но можно их переопределить, например, для вывода сообщения
    val velocity: Int
        get() = 20

    //super<Roamable>.roam - используется для вызова метода roam в наследнике пр переопределении
    fun roam() {
        println("The Roamable is roaming")
    }

}