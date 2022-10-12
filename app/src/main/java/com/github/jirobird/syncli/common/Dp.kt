package com.github.jirobird.syncli.common

import android.content.res.Resources
import kotlin.math.ln
import kotlin.math.pow

class Dp {
    companion object {
        fun toPx(dp: Int ): Int {
            return (dp * Resources.getSystem().displayMetrics.density).toInt()
        }

        fun toDp (px: Int): Float {
            return px / Resources.getSystem().displayMetrics.density
        }

        //TODO: replace in another util
        fun humanReadableByteCount(iBytes: Long, si: Boolean): String? {
            var bytes = iBytes
            val unit = if (si) 1000 else 1024
            val absBytes =
                if (bytes == Long.MIN_VALUE) Long.MAX_VALUE else Math.abs(
                    bytes
                )
            if (absBytes < unit) return "$bytes B"
            var exp =
                (ln(absBytes.toDouble()) / ln(unit.toDouble())).toInt()
            val th =
                (unit.toDouble().pow(exp.toDouble()) * (unit - 0.05)).toLong()
            if (exp < 6 && absBytes >= th - (if (th and 0xfff == 0xd00L) 52 else 0)) exp++
            val pre =
                (if (si) "kMGTPE" else "KMGTPE")[exp - 1].toString() + if (si) "" else "i"
            if (exp > 4) {
                bytes /= unit.toLong()
                exp -= 1
            }
            return String.format(
                "%.1f %sB",
                bytes / unit.toDouble().pow(exp.toDouble()),
                pre
            )
        }
    }
}