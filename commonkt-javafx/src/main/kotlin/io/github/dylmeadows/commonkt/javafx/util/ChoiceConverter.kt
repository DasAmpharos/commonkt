package io.github.dylmeadows.commonkt.javafx.util

import javafx.util.StringConverter
import kotlin.reflect.KClass

class ChoiceConverter<T>(
    type: KClass<T>
) : StringConverter<T>()
        where T : Enum<T>,
              T : Choice {
    private val displayNames: Map<String, T> =
        type.java.enumConstants
            .map { it.displayName to it }
            .toMap()

    override fun toString(`object`: T): String {
        return `object`.displayName
    }

    override fun fromString(string: String): T {
        return displayNames[string] ?: error("unable to find Choice for $string")
    }
}