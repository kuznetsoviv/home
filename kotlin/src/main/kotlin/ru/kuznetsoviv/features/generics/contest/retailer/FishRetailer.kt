package ru.kuznetsoviv.features.generics.contest.retailer

import ru.kuznetsoviv.features.generics.contest.pets.Fish

class FishRetailer: Retailer<Fish> {

    override fun sell(): Fish {
        println("Sell Fish")
        return Fish("")
    }

}
