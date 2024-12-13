package com.letter.learning

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
