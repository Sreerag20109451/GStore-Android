package com.example.gstore_android.data.dao

import android.util.Log
import com.example.gstore_android.data.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthDAOImpl @Inject constructor(val auth : FirebaseAuth, val firestore : FirebaseFirestore) : AuthDAO{

     override suspend fun createUser(name: String, email: String, password: String) : Boolean{
        val uid = auth.currentUser?.uid

        if (uid == null) {
            Log.e("CREATE_USER_FN", "UID is null. User not signed in properly")
            return false
        }

        val user = User(
            uid = uid,
            name = name,
            email = email,
            password = password

        )

         try{
             val existingUid = firestore.collection("USERS").document(uid).get().await()

             if(existingUid.exists()){
                 existingUid.reference.update(user.toMap()).await()
                 return  true
             }
             else{
              firestore.collection("USERS").document(uid).set(user).await()
                 return  true

             }
         }
         catch (exc : Exception){

             return  false


         }

         return  false

    }

    override suspend fun getUser(uid : String): User? {

       val documentSnapShot =  firestore.collection("USERS").document(uid).get().await()
        if(documentSnapShot.exists()) {
            val user : User? = documentSnapShot.toObject(User::class.java)
            return  user
        }

        return null
    }

    override suspend fun signUpUser(name: String, email: String, password: String) : Boolean{

        val signupResponse = auth.createUserWithEmailAndPassword(email, password).await()
        if(signupResponse.user!=null) return true
        return  false

    }

    override  suspend fun  checkIfEmailExisrts(email: String) : Boolean{
        val email  = firestore.collection("USERS").whereEqualTo("email",email).get().await()
        if(email.documents.isNotEmpty()) return true
        return false
    }

    override suspend fun loginUser(email: String, password: String) : Boolean {

        val userlogin = auth.signInWithEmailAndPassword(email, password).await()
        if(userlogin.user!=null) return true
        return false

    }

}