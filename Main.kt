/**
 * FizzBuzz問題
 * - 1からnまで数え上げる（1, 2, ・・・, n）
 * - 3で割り切れるとき、Fizz
 * - 5で割り切れるとき、Buzz
 * - 3でも5でも割り切れるとき、FizzBuzz
 */

// オレオレ方針: ルールの追加がしやすい設計を考えてみる

fun main() {
    FizzBuzz(1, 210).print()
}

/**
 * FizzBuzz
 */
class FizzBuzz(val start: Int, val end: Int) {
    val numberSequence: FizzBuzzNumberSequence
    val ruleList: FizzBuzzRuleList

    init {
        // TODO: 必要なら引数のチェック

        // 数列の生成
        numberSequence = FizzBuzzNumberSequence(start, end)
        // 変換ルールを生成
        ruleList = generateRuleList()
    }

    private fun generateRuleList(): FizzBuzzRuleList {
        return FizzBuzzRuleList(
            listOf(
                FizzBuzzRule(2, "Fuga"),
                FizzBuzzRule(3, "Fizz"),
                FizzBuzzRule(5, "Buzz"),
                FizzBuzzRule(7, "Hoge"),
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
class FizzBuzzNumberSequence(val start: Int, val end: Int) {
    val numbers: List<FizzBuzzNumber>

    init {
        if (start > end) {
            throw IllegalArgumentException("{start} must be <= {end}...")
        }

        numbers = generateSequence(start, end)
    }

    private fun generateSequence(start: Int, end: Int): List<FizzBuzzNumber> {
        return (start..end).toList().map{
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
class FizzBuzzNumber(val value: Int) {
    init {
        if (value <= 0) {
            throw IllegalArgumentException("Not natural number...")
        }
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
class FizzBuzzRuleList(val rules: List<FizzBuzzRule>) {
    init {
        // TODO: 必要なら引数のチェック
    }
    // TODO: "ルールを追加する"を函数として表現するのもいいかも
}

/**
 * FizzBuzzの変換ルール
 * number の倍数の場合に text に変換する
 */
class FizzBuzzRule(val number: Int, val text: String) {
    // TODO: 第一引数を"条件"を表現できるオブジェクトに変更すれば、より汎用的にできそう
    // TODO: textもValue Objectにすべきかも

    init {
        // TODO: 必要なら引数のチェック
    }

    private fun isMultiple(target: FizzBuzzNumber): Boolean {
        return target.value % number === 0
    }

    fun toText(target: FizzBuzzNumber): String? {
        return if (isMultiple(target)) text else ""
    }
}
