package io.github.dylmeadows.commonkt.javafx.node.spinner

import javafx.beans.property.ObjectProperty
import javafx.beans.property.StringProperty
import javafx.scene.control.Spinner

val <T> Spinner<T>.textProperty: StringProperty
    get() = editor.textProperty()

val <T> Spinner<T>.valueProperty: ObjectProperty<T>?
    get() = valueFactory?.valueProperty()

var <T> Spinner<T>.text: String
    get() = editor.text
    set(value) {
        editor.text = value
    }

fun <T> Spinner<T>.setValue(
    value: T
) {
    valueFactory?.value = value
}
