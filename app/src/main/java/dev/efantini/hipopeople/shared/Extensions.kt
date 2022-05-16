package dev.efantini.hipopeople.shared

import dev.efantini.hipopeople.domain.model.Member

fun Member.filter(query: String): Boolean {
    return when {
        this.name.contains(query) -> true
        this.github.contains(query) -> true
        this.hipo.position.contains(query) -> true
        else -> false
    }
}
