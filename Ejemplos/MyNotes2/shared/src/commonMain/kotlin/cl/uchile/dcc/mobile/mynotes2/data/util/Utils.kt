package cl.uchile.dcc.mobile.mynotes2.data.util

import kotlin.random.Random

fun getRandomColor(): Long {
    return Random.nextLong(0xFF000000, 0xFFFFFFFF)
}