package com.dinarastepina.nanaykmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform