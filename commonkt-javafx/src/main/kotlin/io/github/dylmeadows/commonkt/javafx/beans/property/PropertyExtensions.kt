package io.github.dylmeadows.commonkt.javafx.beans.property

import com.sun.javafx.binding.BidirectionalBinding
import javafx.beans.property.*
import javafx.beans.value.*
import kotlin.reflect.KProperty

operator fun <T> ObservableValue<T>.getValue(thisRef: Any, property: KProperty<*>): T = this.value!!
operator fun <T> Property<T>.setValue(thisRef: Any, property: KProperty<*>, value: T?) = setValue(value)

operator fun ObservableIntegerValue.getValue(thisRef: Any, property: KProperty<*>) = get()
operator fun IntegerProperty.setValue(thisRef: Any, property: KProperty<*>, value: Int) = set(value)

operator fun ObservableLongValue.getValue(thisRef: Any, property: KProperty<*>) = get()
operator fun LongProperty.setValue(thisRef: Any, property: KProperty<*>, value: Long) = set(value)

operator fun ObservableFloatValue.getValue(thisRef: Any, property: KProperty<*>) = get()
operator fun FloatProperty.setValue(thisRef: Any, property: KProperty<*>, value: Float) = set(value)

operator fun ObservableDoubleValue.getValue(thisRef: Any, property: KProperty<*>) = get()
operator fun DoubleProperty.setValue(thisRef: Any, property: KProperty<*>, value: Double) = set(value)

operator fun ObservableBooleanValue.getValue(thisRef: Any, property: KProperty<*>) = get()
operator fun BooleanProperty.setValue(thisRef: Any, property: KProperty<*>, value: Boolean) = set(value)

fun ObjectProperty<Int>.bindBidirectional(property: IntegerProperty) {
    BidirectionalBinding.bindNumber(this, property)
}

fun IntegerProperty.bindBidirectional(property: ObjectProperty<Int>) {
    BidirectionalBinding.bindNumber(this, property)
}

fun ObjectProperty<Long>.bindBidirectional(property: LongProperty) {
    BidirectionalBinding.bindNumber(this, property)
}

fun LongProperty.bindBidirectional(property: ObjectProperty<Long>) {
    BidirectionalBinding.bindNumber(this, property)
}

fun ObjectProperty<Float>.bindBidirectional(property: FloatProperty) {
    BidirectionalBinding.bindNumber(this, property)
}

fun FloatProperty.bindBidirectional(property: ObjectProperty<Float>) {
    BidirectionalBinding.bindNumber(this, property)
}

fun ObjectProperty<Double>.bindBidirectional(property: DoubleProperty) {
    BidirectionalBinding.bindNumber(this, property)
}

fun DoubleProperty.bindBidirectional(property: ObjectProperty<Double>) {
    BidirectionalBinding.bindNumber(this, property)
}