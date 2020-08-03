package io.github.dylmeadows.commonkt.javafx.stage

import io.github.dylmeadows.commonkt.javafx.util.Dimension
import javafx.stage.Stage

var Stage.size: Dimension
    get() = Dimension(width, height)
    set(value) {
        width = value.width
        height = value.height
    }
