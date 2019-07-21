package io.github.dylmeadows.commonkt.javafx.node.spinner

import javafx.scene.control.Spinner

val <T> Spinner<T>.valueProperty get() = valueFactory?.valueProperty()

fun <T> Spinner<T>.setValue(value: T) {
    valueFactory?.value = value
}

val <T> Spinner<T>.textProperty get() = editor.textProperty()

var <T> Spinner<T>.text: String
    get() = editor.text
    set(value) {
        editor.text = value
    }