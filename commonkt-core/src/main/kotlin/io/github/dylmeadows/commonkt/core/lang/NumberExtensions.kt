package io.github.dylmeadows.commonkt.core.lang

fun String.isInteger(): Boolean {
    return isNumber(this, String::toInt)
}

fun String.isInteger(radix: Int): Boolean {
    return isNumber(this, radix, String::toInt)
}

fun String.isDouble(): Boolean {
    return isNumber(this, String::toDouble)
}

private fun <T : Number> isNumber(s: String, converter: (String) -> T): Boolean {
    return try {
        converter(s)
        true
    } catch (e: NumberFormatException) {
        false
    }
}

private fun <T : Number> isNumber(s: String, radix: Int, converter: (String, Int) -> T): Boolean {
    return try {
        converter(s, radix)
        true
    } catch (e: NumberFormatException) {
        false
    }
}