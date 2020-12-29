@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package io.github.dylmeadows.commonkt.core.util

class ListStack<T>(
    list: List<T>
) {
    private val list: MutableList<T> = list.toMutableList()

    val size get() = list.size
    val isEmpty get() = list.isEmpty()

    fun push(value: T) {
        list.add(0, value)
    }

    fun peek(): T? = if (!isEmpty) list[0] else null

    fun pop(): T? = if (!isEmpty) list.removeAt(0) else null
}

fun <T> List<T>.asListStack(): ListStack<T> = ListStack(this)
