package ru.kuznetsoviv.features.functions.equals

/**
 *  В kotlin компилятор вместо знака == использует метод equals.
 *  Оператор === используется, чтобы проверить ссылаются ли две переменные на один и тот же объект.
 */
class TestEqualsObjects(var name: String, var family: String) {

    override fun equals(other: Any?): Boolean =
        if (other is TestEqualsObjects) {
            this.name == other.name && this.family == other.family
        } else {
            false
        }

}

fun main() {
    val testEqualsObject1 = TestEqualsObjects("foo1", "foo1")
    val testEqualsObject2 = TestEqualsObjects("foo1", "foo1")
    println(testEqualsObject1 == testEqualsObject2)
    // вернет false
    println(testEqualsObject1 === testEqualsObject2)
}