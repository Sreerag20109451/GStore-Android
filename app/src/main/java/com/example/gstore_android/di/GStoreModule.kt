package com.example.gstore_android.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
class GStoreModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth() : FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun providesFirebaseDB() : FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideFirbaseStorage() :  FirebaseStorage = Firebase.storage


}