package com.example.lab2.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab2.viewmodel.CompanyViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.items



@Composable
fun CompanyListScreen(companyViewModel: CompanyViewModel = viewModel()) {
    val companies = companyViewModel.companies.collectAsState().value

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(companies) { company ->
            CompanyItem(company)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}