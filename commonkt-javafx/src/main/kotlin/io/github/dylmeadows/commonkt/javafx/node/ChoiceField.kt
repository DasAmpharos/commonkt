package io.github.dylmeadows.commonkt.javafx.node

import io.github.dylmeadows.commonkt.javafx.beans.property.getValue
import io.github.dylmeadows.commonkt.javafx.beans.property.setValue
import io.github.dylmeadows.commonkt.javafx.util.Choice
import io.github.dylmeadows.commonkt.javafx.util.ChoiceConverter
import javafx.collections.FXCollections
import javafx.scene.control.ChoiceBox

@Suppress("MemberVisibilityCanBePrivate")
class ChoiceField<T>(
    choiceBox: ChoiceBox<T>
) where T : Enum<T>,
        T : Choice {

    val valueProperty = choiceBox.valueProperty()!!
    var value: T by valueProperty
}

inline fun <reified T> ChoiceBox<T>.asChoiceField(): ChoiceField<T> where T : Enum<T>,
                                                                          T : Choice {
    val choices = T::class.java.enumConstants
    items = FXCollections.observableArrayList(*choices)
    converter = ChoiceConverter(T::class)
    return ChoiceField(this)
}
