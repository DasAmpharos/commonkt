package io.github.dylmeadows.commonkt.javafx.node

import io.github.dylmeadows.commonkt.javafx.util.FxSubscription
import javafx.beans.binding.BooleanExpression
import javafx.beans.value.ChangeListener
import javafx.scene.Node

fun Node.showWhen(condition: BooleanExpression): FxSubscription {
    return hideWhen(condition.not())
}

fun Node.hideWhen(condition: BooleanExpression): FxSubscription {
    visibleProperty().bind(condition.not())
    managedProperty().bind(visibleProperty())
    return object : FxSubscription {
        private val listener = onChange<Boolean> { autosize() }

        init {
            condition.addListener(listener)
        }

        override fun cancel() {
            condition.removeListener(listener)
        }
    }
}

private fun <T> onChange(onChange: () -> Unit): ChangeListener<T> {
    return ChangeListener { _, _, _ -> onChange() }
}