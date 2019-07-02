package io.github.dylmeadows.commonkt.core.io

import java.io.InputStream
import java.net.URL

interface Resource {
    val path: String
}

val Resource.url: URL? get() = classLoader.getResource(path)

val Resource.inputStream: InputStream? get() = classLoader.getResourceAsStream(path)

private val classLoader get() = Resource::class.java.classLoader