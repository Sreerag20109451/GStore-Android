package com.example.gstore_android.di

import android.app.Application
import android.content.Context
import androidx.core.content.ContextCompat.getString
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.example.gstore_android.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext


@Module
@InstallIn(ViewModelComponent::class)
class GStoreModule {

    @Provides
    fun providesFirebaseAuth() : FirebaseAuth = Firebase.auth

    @Provides
    fun providesFirebaseDB() : FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideFirbaseStorage() :  FirebaseStorage = Firebase.storage


    @Provides
    fun provideGoogleIdentity(app : Application) : GetGoogleIdOption =  GetGoogleIdOption.Builder()
        .setServerClientId(app.getString(R.string.google_client))
        .setFilterByAuthorizedAccounts(false)
        .build()


    @Provides
    fun provideCredentialManager(@ApplicationContext context: Context) : CredentialManager = CredentialManager.create(context)

    @Provides
    fun provideCredentialRequest(googleId : GetGoogleIdOption)  : GetCredentialRequest =  GetCredentialRequest.Builder()
        .addCredentialOption(googleId)
        .build()






}