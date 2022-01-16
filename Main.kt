/**
 * FizzBuzz問題
 * - 1からnまで数え上げる（1, 2, ・・・, n）
 * - 3で割り切れるとき、Fizz
 * - 5で割り切れるとき、Buzz
 * - 3でも5でも割り切れるとき、FizzBuzz
 */

// オレオレ方針: ルールの追加がしやすい設計を考えてみる

fun main() {
    val start = FizzBuzzNumber(1)
    val end = FizzBuzzNumber(100)
    FizzBuzz(start, end).print()
}

/**
 * FizzBuzz
 */
class FizzBuzz constructor(_start: FizzBuzzNumber, _end: FizzBuzzNumber) {
    val numberSequence: FizzBuzzNumberSequence
    val ruleList: FizzBuzzRuleList

    init {
        // TODO: 必要なら引数のチェック

        // 数字列の生成
        numberSequence = FizzBuzzNumberSequence(_start, _end)
        // 変換ルールを生成
        ruleList = generateRuleList()
    }

    private fun generateRuleList(): FizzBuzzRuleList {
        return FizzBuzzRuleList(
            listOf(
                FizzBuzzRule(FizzBuzzNumber(3), "Fizz"),
                FizzBuzzRule(FizzBuzzNumber(5), "Buzz"),
            )
        )
    }

    fun print(): Unit {
        numberSequence.print(ruleList)
    }
}

/**
 * FizzBuzz数列
 */
class FizzBuzzNumberSequence constructor(_start: FizzBuzzNumber, _end: FizzBuzzNumber) {
    val numbers: List<FizzBuzzNumber>

    init {
        // TODO: 必要なら引数のチェック

        numbers = generateSequence(_start, _end)
    }

    private fun generateSequence(_start: FizzBuzzNumber, _end: FizzBuzzNumber): List<FizzBuzzNumber> {
        return ((_start.value)..(_end.value)).toList().map{
            FizzBuzzNumber(it)
        }
    }

    fun print(ruleList: FizzBuzzRuleList): Unit {
        numbers.forEach{ number ->
            // println(number.toText(ruleList))
            println(String.format("%3s: %s", number.value, number.toText(ruleList)))
        }
    }
}

/**
 * FizzBuzz数
 */
class FizzBuzzNumber constructor(_value: Int) {
    val value: Int

    init {
        if (_value <= 0) {
            throw IllegalArgumentException("Not natural number...")
        }

        value = _value
    }

    fun toText(ruleList: FizzBuzzRuleList): String {
        // var text = ""
        // ruleList.rules.forEach{ rule ->
        //     text += rule.toText(this)
        // }
        var text = ruleList.rules.map{ rule ->
            rule.toText(this)
        }.joinToString("")
        // どのルールにもマッチしなかった場合
        if (text.isEmpty()) {
            text = value.toString()
        }
        return text
    }
}

/**
 * FizzBuzzルールのリスト
 */
class FizzBuzzRuleList constructor(_rules: List<FizzBuzzRule>) {
    val rules: List<FizzBuzzRule>

    init {
        // TODO: 必要なら引数のチェック

        rules = _rules
    }
}

/**
 * FizzBuzzの変換ルール
 */
class FizzBuzzRule constructor(_number: FizzBuzzNumber, _text: String) {
    // TODO: 第一引数を"条件"を表現できるオブジェクトに変更すれば、より汎用的にできそう
    val number: FizzBuzzNumber
    // TODO: textもValue Objectにすべきかも
    val text: String

    init {
        // TODO: 必要なら引数のチェック

        number = _number
        text = _text
    }

    private fun isMultiple(target: FizzBuzzNumber): Boolean {
        return target.value % number.value === 0
    }

    fun toText(target: FizzBuzzNumber): String? {
        return if (isMultiple(target)) text else ""
    }
}
