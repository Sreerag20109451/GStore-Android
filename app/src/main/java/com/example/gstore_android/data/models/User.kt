package com.example.gstore_android.data.models

data class User(val uid : String, val name : String, val email: String, val password: String){
    fun toMap() : Map<String, Any>{

        return  mapOf(

            "name" to name,
            "email" to email,
            "password" to password
        )

    }
}