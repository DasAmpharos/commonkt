package io.github.dylmeadows.commonkt.javafx.node

import io.github.dylmeadows.commonkt.javafx.util.FxSubscription
import javafx.beans.binding.BooleanExpression
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.scene.Node

fun Node.showWhen(condition: BooleanExpression): FxSubscription {
    return hideWhen(condition.not())
}

fun Node.hideWhen(condition: BooleanExpression): FxSubscription {
    visibleProperty().bind(condition.not())
    managedProperty().bind(visibleProperty())
    return object : FxSubscription, ChangeListener<Boolean> {
        init {
            condition.addListener(this)
        }

        override fun changed(observable: ObservableValue<out Boolean>?, oldValue: Boolean?, newValue: Boolean?) {
            autosize()
        }

        override fun cancel() {
            condition.removeListener(this)
        }
    }
}

fun Node.setOnFocusLost(onFocusLost: () -> Unit): FxSubscription {
    return object : FxSubscription, ChangeListener<Boolean> {
        init {
            focusedProperty().addListener(this)
        }

        override fun changed(observable: ObservableValue<out Boolean>?, oldValue: Boolean?, newValue: Boolean?) {
            if (newValue != null && !newValue) {
                onFocusLost()
            }
        }

        override fun cancel() {
            focusedProperty().removeListener(this)
        }
    }
}
