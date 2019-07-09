package io.github.dylmeadows.commonkt.javafx.scene

import javafx.scene.Parent
import javafx.scene.Scene

fun Parent.asScene(): Scene {
    return Scene(this)
}