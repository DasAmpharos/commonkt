package io.github.dylmeadows.commonkt.javafx.scene

import io.github.dylmeadows.commonkt.core.io.Resource
import javafx.scene.Scene

fun Scene.addCss(resource: Resource) {
    stylesheets.add(resource.path)
}
