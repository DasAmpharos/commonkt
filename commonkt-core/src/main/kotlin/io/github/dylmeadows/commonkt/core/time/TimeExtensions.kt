@file:Suppress("unused")

package io.github.dylmeadows.commonkt.core.time

import java.time.Duration
import java.time.Instant
import java.time.temporal.ChronoUnit

val INDEFINITE = Duration.between(Instant.MIN, Instant.MAX)!!
inline val Duration.isIndefinite: Boolean get() = this == INDEFINITE

inline val Long.days: Duration get() = Duration.ofDays(this)
inline val Long.hours: Duration get() = Duration.ofHours(this)
inline val Long.minutes: Duration get() = Duration.ofMinutes(this)
inline val Long.seconds: Duration get() = Duration.ofSeconds(this)
inline val Long.milliseconds: Duration get() = Duration.ofMillis(this)
inline val Long.nanoseconds: Duration get() = Duration.ofNanos(this)

fun Duration.toSeconds(): Long {
    return get(ChronoUnit.SECONDS)
}

fun List<Duration>.sum(): Duration {
    return if (!contains(INDEFINITE)) {
        Duration.ofMillis(map(Duration::toMillis).sum())
    } else {
        INDEFINITE
    }
}