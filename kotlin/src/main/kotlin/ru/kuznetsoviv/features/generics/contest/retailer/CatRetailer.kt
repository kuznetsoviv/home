package ru.kuznetsoviv.features.generics.contest.retailer

import ru.kuznetsoviv.features.generics.contest.pets.Cat

class CatRetailer: Retailer<Cat> {

    override fun sell(): Cat {
        println("Sell cat")
        return  Cat("")
    }

}
