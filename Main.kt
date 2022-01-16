/**
 * FizzBuzz問題
 * - 1からnまで数え上げる（1, 2, ・・・, n）
 * - 3で割り切れるとき、Fizz
 * - 5で割り切れるとき、Buzz
 * - 3でも5でも割り切れるとき、FizzBuzz
 */

fun main() {
    for (i in 1..100) {
        if (i % 5 === 0 && i % 3 === 0) {
            println("FizzBuzz")
        } else if (i % 5 === 0) {
            println("Buzz")
        } else if (i % 3 === 0) {
            println("Fizz")
        } else {
            println(i)
        }
    }
}
