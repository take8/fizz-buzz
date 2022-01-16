/**
 * FizzBuzz問題
 * - 1からnまで数え上げる（1, 2, ・・・, n）
 * - 3で割り切れるとき、Fizz
 * - 5で割り切れるとき、Buzz
 * - 3でも5でも割り切れるとき、FizzBuzz
 */

fun main() {
    val start = FizzBuzzNumber(1)
    val end = FizzBuzzNumber(100)
    FizzBuzz(start, end).print()
}

class FizzBuzz constructor(_start: FizzBuzzNumber, _end: FizzBuzzNumber) {
    val numberSequence: FizzBuzzNumberSequence

    init {
        // TODO: 必要なら引数のチェック
    
        numberSequence = FizzBuzzNumberSequence(_start, _end)
    }

    fun print() {
        numberSequence.print()
    }
}

class FizzBuzzNumberSequence constructor(_start: FizzBuzzNumber, _end: FizzBuzzNumber) {
    val values: List<FizzBuzzNumber>

    init {
        // TODO: 必要なら引数のチェック

        values = generateSequence(_start, _end)
    }
    
    private fun generateSequence(_start: FizzBuzzNumber, _end: FizzBuzzNumber): List<FizzBuzzNumber> {
        return ((_start.value)..(_end.value)).toList().map{
            FizzBuzzNumber(it)
        }
    }

    fun print() {
        // TODO: iterator使ったらいい感じになる?
        values.forEach{ value ->
            value.print()
        }
    }
}

class FizzBuzzNumber constructor(_value: Int) {
    val value: Int

    init {
        if (_value <= 0) {
            throw IllegalArgumentException("Not natural number...")
        }
    
        value = _value
    }
    
    fun isFizz(): Boolean {
        return value % 3 === 0
    }
    
    fun isBuzz(): Boolean {
        return value % 5 === 0
    }
    
    fun print() {
        var result = ""

        if (isFizz()) {
            result += "Fizz"
        }
        if (isBuzz()) {
            result += "Buzz"
        }
        if (result.isEmpty()) {
            result = value.toString()
        }

        println(result)
    }
}
