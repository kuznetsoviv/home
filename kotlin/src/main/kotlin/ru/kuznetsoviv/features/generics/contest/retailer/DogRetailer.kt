package ru.kuznetsoviv.features.generics.contest.retailer

import ru.kuznetsoviv.features.generics.contest.pets.Dog

class DogRetailer : Retailer<Dog> {

    override fun sell(): Dog {
        println("Sell dog")
        return Dog("")
    }

}
