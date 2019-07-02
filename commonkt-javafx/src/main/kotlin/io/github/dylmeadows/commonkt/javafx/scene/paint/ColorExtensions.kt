package io.github.dylmeadows.commonkt.javafx.scene.paint

import javafx.scene.paint.Color
import java.util.stream.Stream

fun Color.toHex(withOpacity: Boolean = false): String {
    val elements = if (withOpacity)
        Stream.of(red, green, blue, opacity)
    else
        Stream.of(red, green, blue)
    return elements
        .map { (it * 255).toInt() and 0xff }
        .map { String.format("%02x", it) }
        .reduce("#", String::plus)
}

fun Color.withRed(red: Double): Color {
    return Color(red, green, blue, opacity)
}

fun Color.withGreen(green: Double): Color {
    return Color(red, green, blue, opacity)
}

fun Color.withBlue(blue: Double): Color {
    return Color(red, green, blue, opacity)
}

fun Color.withOpacity(opacity: Double): Color {
    return Color(red, green, blue, opacity)
}

fun Color.withHue(hue: Double): Color {
    return Color.hsb(hue, saturation, brightness, opacity)
}

fun Color.withSaturation(saturation: Double): Color {
    return Color.hsb(hue, saturation, brightness, opacity)
}

fun Color.withBrightness(brightness: Double): Color {
    return Color.hsb(hue, saturation, brightness, opacity)
}

fun Color.deriveHue(hueShift: Double): Color {
    return withHue(derive(hue, hueShift))
}

fun Color.deriveSaturation(percent: Double): Color {
    return withSaturation(derive(saturation, percent))
}

fun Color.deriveBrightness(percent: Double): Color {
    return withBrightness(derive(brightness, percent))
}

private fun derive(value: Double, percent: Double): Double {
    return when {
        percent < 0 -> Math.max(value + percent, 0.0)
        percent > 0 -> Math.min(value + percent, 1.0)
        else -> value
    }
}