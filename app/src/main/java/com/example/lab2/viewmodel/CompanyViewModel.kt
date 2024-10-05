package com.example.lab2.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.lab2.data.model.Company
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class CompanyViewModel : ViewModel() {

    // StateFlow to hold companies list
    private val _companies = MutableStateFlow<List<Company>>(emptyList())
    val companies: StateFlow<List<Company>> = _companies.asStateFlow()

    private val firestore = FirebaseFirestore.getInstance()

    init {
        fetchCompanies()
    }

    // Fetch companies and update StateFlow through a coroutine
    private fun fetchCompanies() {
        viewModelScope.launch {
            firestore.collection("Companies")
                .get()
                .addOnSuccessListener { result ->
                    val companyList = result.documents.mapNotNull { it.toObject(Company::class.java) }
                    _companies.value = companyList
                    Log.d("company list", "$companyList")
                }
                .addOnFailureListener { e ->
                    println("Something went wrong $e")
                }
        }
    }
}