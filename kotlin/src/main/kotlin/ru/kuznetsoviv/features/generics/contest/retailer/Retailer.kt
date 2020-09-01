package ru.kuznetsoviv.features.generics.contest.retailer

/**
 * out - ковариантность - можно использовать подтип вместо супертипа
 */
interface Retailer<out T> {

    fun sell(): T

}