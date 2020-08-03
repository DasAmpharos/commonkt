package io.github.dylmeadows.commonkt.core.lang

fun String.isInt(radix: Int = 10): Boolean = isNumber(String::toInt, radix)

fun String.isDouble(): Boolean = isNumber(String::toDouble)

private fun <T : Number> String.isNumber(
    converter: (String) -> T
): Boolean {
    return kotlin.runCatching { converter(this) }
        .isSuccess
}

private fun <T : Number> String.isNumber(
    converter: (String, Int) -> T,
    radix: Int
): Boolean {
    return kotlin.runCatching { converter(this, radix) }
        .isSuccess
}