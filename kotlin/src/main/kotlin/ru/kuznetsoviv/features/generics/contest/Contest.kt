package ru.kuznetsoviv.features.generics.contest

import ru.kuznetsoviv.features.generics.contest.pets.Cat
import ru.kuznetsoviv.features.generics.contest.pets.Fish
import ru.kuznetsoviv.features.generics.contest.pets.Pet
import ru.kuznetsoviv.features.generics.contest.vet.Vet

/**
 * in - означает контрвариантность - можно использовать супертип вместо подтипа
 */
class Contest<T : Pet>(var vet: Vet<in T>) {
    private val scores = mutableMapOf<T, Int>()

    fun addScore(pet: T, score: Int = 0) {
        if (score >= 0) scores[pet] = score
    }

    fun getWinners(): MutableSet<T> {
        val winners = mutableSetOf<T>()
        val highScore = scores.values.max()
        for ((pet, score) in scores) {
            if (score == highScore) winners.add(pet)
        }
        return winners
    }

    fun main(args: Array<String>) {
        val catFuzz = Cat("Fuzz LightYear")
        val catKatsu = Cat("Katsu")
        val fishFinny = Cat("Finny McGraw")

        val catVet = Vet<Cat>()
        val fishVet = Vet<Fish>()
        val petVet = Vet<Pet>()

        catVet.treat(catFuzz)
        petVet.treat(catKatsu)
        petVet.treat(fishFinny)

        val catContest = Contest(catVet)
        catContest.addScore(catFuzz, 50)
        catContest.addScore(catKatsu, 100)
        val topCat = catContest.getWinners().first()
        println("Cat winner is ${topCat.name}")

        val petContest = Contest(petVet)
        petContest.addScore(catFuzz, 100)
        petContest.addScore(fishFinny, 35)
        val topPet = petContest.getWinners().first()
        println("Pet winner is ${topPet.name}")

    }

}
