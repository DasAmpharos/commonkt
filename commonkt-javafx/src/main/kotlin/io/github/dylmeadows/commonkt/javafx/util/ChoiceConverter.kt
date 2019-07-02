package io.github.dylmeadows.commonkt.javafx.util

import javafx.util.StringConverter
import kotlin.reflect.KClass

class ChoiceConverter<T>(type: KClass<T>) : StringConverter<T>()
        where T : Enum<T>,
              T : Choice {

    private val displayNameMap: Map<String, T>

    init {
        val displayNameMap = HashMap<String, T>()
        for (enumConstant in type.java.enumConstants) {
            displayNameMap[enumConstant.displayName] = enumConstant
        }
        this.displayNameMap = displayNameMap
    }

    override fun toString(`object`: T): String {
        return `object`.displayName
    }

    override fun fromString(string: String): T {
        return displayNameMap[string] ?: error("unable to find Choice for $string")
    }
}