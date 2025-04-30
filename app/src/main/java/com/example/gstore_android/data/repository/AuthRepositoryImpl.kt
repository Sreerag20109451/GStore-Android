package com.example.gstore_android.data.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.example.gstore_android.data.dao.AuthDAOImpl
import com.example.gstore_android.data.models.User
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(val auth : FirebaseAuth ,
                                             val authDAOImpl : AuthDAOImpl,
                                             val storage: FirebaseStorage,
                                             val googleIdOpton : GetGoogleIdOption, val credManager: CredentialManager, val credRequest : GetCredentialRequest)  : AuthInterface {

    override suspend fun signUpUser(name: String, email: String, password: String): Boolean {

        Log.d("SIGNINREPOSTART", "in-repo")
        Log.d("RepoSsdhbbj","It works here")
        val isSignedIn = authDAOImpl.signUpUser(name, email, password)
        try{
            if (isSignedIn) {
                Log.d("Repo-signed_In","It works here")
                return authDAOImpl.createUser(name, email, password , photouri = null)
            }
        }
        catch (ex : Exception){
            Log.d("SIGN_IN_FAILURE",ex.localizedMessage)
        }


        return false


    }

    override suspend fun getUserData(uid: String): User? {

        val user = authDAOImpl.getUser(uid)
        return user
    }

    override suspend fun emailAlreadyExists(email: String): Boolean {
        Log.d("AFTERCHECKREPO", "email check repo")
        if (authDAOImpl.checkIfEmailExisrts(email)) return true
        return false
    }

    override suspend fun loginUser(email: String, password: String): String? {

        val userToken = authDAOImpl.loginUser(email, password)
        Log.d("Hehehe", "$userToken  is fuck ")
        return userToken
    }

    override suspend fun googleSignup(context : Context): String? {

        Log.d("GSIGN_REP)", "its in repo")


        try {

            val credential = credManager.getCredential(context , request = credRequest ).credential

            Log.d("CRED111)", "${credential}")

            if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                // Create Google ID Token
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

                Log.d("GIDTOKEN", "${credential}")

                // Sign in to Firebase with using the token
                val userIdToken =
                    authDAOImpl.firebaseAuthWithGoogle(googleIdTokenCredential.idToken)
                return userIdToken.toString()
            } else {

                return null
            }
        } catch (err: Exception) {

            Log.d("EX111", "${err.message}")

        }

        return null
    }

    override suspend fun UploadProfilePhoto(imageUri: Uri): Uri? {
        // Create file metadata including the content type

        return  authDAOImpl.UploadProfilePhoto(imageUri)
    }

}



