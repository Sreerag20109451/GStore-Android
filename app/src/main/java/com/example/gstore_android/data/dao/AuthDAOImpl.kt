package com.example.gstore_android.data.dao

import android.net.Uri
import android.util.Log
import com.example.gstore_android.data.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthDAOImpl @Inject constructor(val auth : FirebaseAuth, val storage: FirebaseStorage, val firestore : FirebaseFirestore) : AuthDAO{

     override suspend fun createUser(name: String, email: String, password: String?, photouri: String?) : Boolean{
        val uid = auth.currentUser?.uid

         Log.d("UID_here", "uid is ${uid}")

        if (uid == null) {
            Log.e("CREATE_USER_FN", "UID is null. User not signed in properly")
            return false
        }

         Log.d("CREATE_START_DB", "user is creating")

        val user = User(
            uid = uid,
            name = name,
            email = email,
            password = password,
            photouri =  photouri

        )

         try{
             Log.d("CREATE_START_DB22", "db will call next")
             val existingUid = firestore.collection("USERS").document(uid).get().await()

             if(existingUid.exists()){
                 existingUid.reference.update(user.toMap()).await()
                 return  true
             }
             else{
                 Log.d("CREATE_START_DB2425265", "db will create user")
              firestore.collection("USERS").document(uid).set(user).await()
                 return  true

             }
         }
         catch (exc : Exception){

             Log.d("EXCEPTION_CREATION_HERE", "db exc is ${exc.localizedMessage}")

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

        Log.d("SIGNDAOREACHED", "sign in dao object called")
        try {
            val signupResponse = auth.createUserWithEmailAndPassword(email, password).await()
            Log.d("SIGNUPRESPONSEHERE" , "resp is $signupResponse")
            if(signupResponse.user!=null) return true
        }
        catch (ex : Exception){

            Log.d("ExceptionMSHDAO", "${ex.localizedMessage}")

            return  false
        }
        return  false
    }

    override  suspend fun  checkIfEmailExisrts(email: String) : Boolean{
        val email  = firestore.collection("USERS").whereEqualTo("email",email).get().await()
        if(email.documents.isNotEmpty()) return true
        return false
    }

    override suspend fun loginUser(email: String, password: String) : String? {

        try {
            val userlogin = auth.signInWithEmailAndPassword(email, password).await()
            if (userlogin.user != null) {
                val token = userlogin.user?.getIdToken(false)?.await()?.token
                Log.d("TOKEN_DEBUG", "Token: $token")
                return token
            } else return null

        } catch (e: Exception) {

            return null
        }
    }


    override suspend fun firebaseAuthWithGoogle(idToken: String) : String? {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        val  signinRqst = auth.signInWithCredential(credential).await()


        if(signinRqst.user!=null) {

            if(signinRqst.additionalUserInfo?.isNewUser == true) {

                try {

                    val name = signinRqst.user?.displayName.toString()
                    val email = signinRqst.user?.email.toString()
                    val photouri = signinRqst.user?.photoUrl.toString()
                    createUser(name, email, password = null, photouri = photouri)
                }
                catch (ex : Exception){
                    return null
                }
            }
            return  signinRqst.user?.getIdToken(true)?.await().toString()
        }
        else return null
    }

    override suspend fun UploadProfilePhoto(imageUri: Uri): Uri? {
        var uid = auth.uid
        var user = auth?.currentUser?.displayName

        Log.d("UPLOADPR", "next is uplpad")
        var storageRef = storage.reference.child("Users/$uid/${user}.png")

        Log.d("UPLOADPRO1STORAGEINITIATED", "next is ${storageRef}")
        var uploadTask = storageRef.putFile(imageUri).await()
        val photourl = storageRef.downloadUrl.await()
        if(photourl==null) return null

        Log.d("UPLOADPRO1STORAGEUPLOAD", "next is ${photourl}")

       try{
           firestore.collection("USERS")
               .document(uid.toString())
               .update("photouri", photourl)
               .await()
       }
       catch(exc: Exception){
           Log.d("EXCEPTOPN", "${exc.message}")
           return null
       }

        Log.d("UPLOADPROCOLLECTIONDOWNLOAD", "Downloaded ${photourl}")


        return photourl
    }



}