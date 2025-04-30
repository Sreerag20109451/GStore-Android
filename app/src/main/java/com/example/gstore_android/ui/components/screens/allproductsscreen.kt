package com.example.gstore_android.ui.components.screens


import ProductWideCard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gstore_android.data.models.Product
import com.example.gstore_android.viewmodels.ProductsViewModel


@Composable
fun AllProductsPage(productsViewModel : ProductsViewModel = hiltViewModel<ProductsViewModel>()) {
    var selectedFilter by remember { mutableStateOf("All") }
    var products = productsViewModel.displayProducts.value
    fun resetFilter(){

        productsViewModel.displayProducts.value = productsViewModel.products.value
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CustomSearchBar(productsViewModel)

        FilterBar(productsViewModel, onResetFilters = {resetFilter()})

        Spacer(modifier = Modifier.height(16.dp))


        Spacer(modifier = Modifier.height(16.dp))

        if (products != null) { // Lazy Column to display the products
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 16.dp)
            ) {
                items(products) { product ->
                    ProductWideCard(product = product)
                }
            }
        }

    }

    @Composable
    fun ProductCard(product: Product) {
        // Placeholder for your product card UI
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            elevation = CardDefaults.elevatedCardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "${product.price} USD",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun CustomSearchBar(productsViewModel: ProductsViewModel) {
    var searchString by remember { mutableStateOf("") }

    val colors = MaterialTheme.colorScheme
        OutlinedTextField(
            value = searchString,
            onValueChange = { searchString = it },
            label = { Text("Search Products") },
            placeholder = { Text("Search...") },
            modifier = Modifier
                .height(76.dp).fillMaxWidth(),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { productsViewModel.searchByNameOrCategories(searchString) }, modifier = Modifier
                    .height(56.dp)) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search by Name or categories",
                        tint = colors.tertiary
                    )
                }
            }
        )



}


@Composable
fun SortByPriceUI(productsViewModel: ProductsViewModel) {

    var isFiltered by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    var selectedSort by remember { mutableStateOf("asc") }
    val colors  = MaterialTheme.colorScheme

    Box( contentAlignment = Alignment.Center) {
        OutlinedButton(
            onClick = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Sort by Price", fontSize = 10.sp, color=  if(isFiltered) colors.primary else colors.secondary)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            DropdownMenuItem(onClick = {
                isFiltered = true
                selectedSort = "asc"
                productsViewModel.sortByPrice("asc")
                expanded = false
            }, text =  {
                Text("Lowest to Highest")
            })

            DropdownMenuItem(onClick = {
                isFiltered =true
                selectedSort = "desc"
                productsViewModel.sortByPrice("desc")
                expanded = false
            }, text = {
                Text("Highest to Lowest")
            })
        }
    }
}

@Composable
fun FilterPriceUI(productsViewModel: ProductsViewModel) {

    var isFiltered by remember { mutableStateOf(false) }
    val colors  = MaterialTheme.colorScheme
    var expanded by remember { mutableStateOf(false) }
    var selectedSort by remember { mutableStateOf(0) }

    Box( contentAlignment = Alignment.Center) {
        OutlinedButton(
            onClick = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "FIlter by Price", fontSize = 10.sp , color = if(isFiltered) colors.primary else colors.secondary)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            DropdownMenuItem(onClick = {
                isFiltered = true
                selectedSort = 1
                productsViewModel.filterByPrice(selectedSort)
                expanded = false
            }, text =  {
                Text("0-2")
            })

            DropdownMenuItem(onClick = {
                isFiltered = true
                selectedSort = 2
                productsViewModel.filterByPrice(selectedSort)
                expanded = false
            }, text = {
                Text("2-6")
            })
            DropdownMenuItem(onClick = {
                isFiltered = true
                selectedSort = 3
                productsViewModel.filterByPrice(selectedSort)
                expanded = false
            }, text = {
                Text("Greater than 6")
            })
        }
    }
}

@Composable
fun FilterByCategories(productsViewModel: ProductsViewModel) {
    val colors  = MaterialTheme.colorScheme
    var expanded by remember { mutableStateOf(false) }
    var selectedSort by remember { mutableStateOf("") }

    var isFiltered by remember { mutableStateOf(false) }

    Box( contentAlignment = Alignment.Center) {
        OutlinedButton(
            onClick = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Filter by Categories", fontSize = 10.sp,  color = if(isFiltered) colors.primary else colors.secondary)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            DropdownMenuItem(onClick = {
                isFiltered =true
                selectedSort = "Fruits"
                productsViewModel.filterByCategories(selectedSort)
                expanded = false
            }, text =  {
                Text("Fruits")
            })

            DropdownMenuItem(onClick = {
                isFiltered = true
                selectedSort = "Vegetable"
                productsViewModel.filterByCategories(selectedSort)
                expanded = false
            }, text = {
                Text("Vegetable")
            })
            DropdownMenuItem(onClick = {
                isFiltered =true
                selectedSort = "Dairy"
                productsViewModel.filterByCategories(selectedSort)
                expanded = false
            }, text = {
                Text("Diary")
            })
        }
    }
}



@Composable
fun FilterBar(
    productsViewModel: ProductsViewModel,
    onResetFilters: () -> Unit
) {


    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {

        // Row of filters with spacing
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Filter by Category
            Box(modifier = Modifier.weight(1f)) {
                FilterByCategories(productsViewModel = productsViewModel)
            }

            // Filter by Price Range
            Box(modifier = Modifier.weight(1f)) {
                FilterPriceUI(productsViewModel = productsViewModel)
            }

            // Sort by Price
            Box(modifier = Modifier.weight(1f)) {
                SortByPriceUI(productsViewModel = productsViewModel)
            }
        }

        // Reset Button
        Button(
            onClick = { onResetFilters() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Reset Filters")
        }
    }
}

