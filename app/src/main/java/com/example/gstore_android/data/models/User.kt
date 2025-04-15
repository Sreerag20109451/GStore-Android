package com.example.gstore_android.data.models

data class User(
    var uid: String = "",
    var name: String = "",
    var email: String = "",
    var password: String? = null,
    var photouri: String? = null
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "name" to name,
            "email" to email,
            "password" to password,
            "photouri" to photouri
        ) as Map<String, Any>
    }
}
