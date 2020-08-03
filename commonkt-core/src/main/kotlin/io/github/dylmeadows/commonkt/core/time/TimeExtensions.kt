@file:Suppress("unused")

package io.github.dylmeadows.commonkt.core.time

import io.github.dylmeadows.commonkt.core.util.sumByLong
import java.time.Duration
import java.time.Instant

val INDEFINITE: Duration = Duration.between(Instant.MAX, Instant.MIN)
inline val Duration.isIndefinite: Boolean get() = this == INDEFINITE

inline val Long.days: Duration get() = Duration.ofDays(this)
inline val Long.hours: Duration get() = Duration.ofHours(this)
inline val Long.minutes: Duration get() = Duration.ofMinutes(this)
inline val Long.seconds: Duration get() = Duration.ofSeconds(this)
inline val Long.milliseconds: Duration get() = Duration.ofMillis(this)
inline val Long.nanoseconds: Duration get() = Duration.ofNanos(this)

fun List<Duration>.sum(): Duration {
    return if (!contains(INDEFINITE)) {
        Duration.ofMillis(sumByLong(Duration::toMillis))
    } else {
        INDEFINITE
    }
}