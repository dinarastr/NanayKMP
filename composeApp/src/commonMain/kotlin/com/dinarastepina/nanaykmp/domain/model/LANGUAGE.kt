package com.dinarastepina.nanaykmp.domain.model

enum class LANGUAGE(val value: String) {
    NANAY("на̄ни"),
    RUSSIAN("русский");

    companion object {
        fun fromString(language: String): LANGUAGE {
            return entries.find { it.value == language } ?: RUSSIAN
        }

        fun targetLanguage(currentLanguage: LANGUAGE): LANGUAGE {
            return if (currentLanguage == NANAY) RUSSIAN else NANAY
        }
    }
}