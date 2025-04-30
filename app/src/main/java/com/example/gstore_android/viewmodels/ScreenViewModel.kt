package com.example.gstore_android.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ScreenViewModel @Inject constructor() : ViewModel() {


    var isCategoryOpen =  mutableStateOf(true)
    var isOrdersOpen  =  mutableStateOf(false)
    var isProfileOpen =  mutableStateOf(false)
    var isSearchOpen =   mutableStateOf(false)
    var iscartOpen = mutableStateOf(false)


    fun openCategoryScreen(){

        iscartOpen.value = false
        isCategoryOpen.value = true
        isOrdersOpen.value = false
        isProfileOpen.value =false
        isSearchOpen.value = false
    }


    fun openOrdersScreen(){
        iscartOpen.value = false

        isCategoryOpen.value = false
        isOrdersOpen.value = true
        isProfileOpen.value =false
        isSearchOpen.value = false
    }

    fun openSearchScreen(){
        iscartOpen.value = false

        isCategoryOpen.value = false
        isOrdersOpen.value = false
        isProfileOpen.value = false
        isSearchOpen.value = true
    }
    fun openProfileScreen(){
        iscartOpen.value = false

        isCategoryOpen.value = false
        isOrdersOpen.value = false
        isProfileOpen.value = true
        isSearchOpen.value = false
    }
    fun openCartScreen(){
        iscartOpen.value = true

        isCategoryOpen.value = false
        isOrdersOpen.value = false
        isProfileOpen.value = false
        isSearchOpen.value = false
    }

}