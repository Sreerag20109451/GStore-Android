package com.example.gstore_android.ui.components.screens


import ProductWideCard
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gstore_android.data.models.Product
import com.example.gstore_android.viewmodels.ProductsViewModel


@Composable
fun AllProductsPage(productsViewModel : ProductsViewModel = hiltViewModel<ProductsViewModel>()) {
    // State for the search query

    // State for the filter options (you can replace this with real filter logic later)
    var selectedFilter by remember { mutableStateOf("All") }

    var products = productsViewModel.displayProducts.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Search Bar
        CustomSearchBar(productsViewModel)
            SortByPriceUI(productsViewModel)
            FilterPriceUI(productsViewModel)

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
    fun FilterBar(selectedFilter: String, onFilterChange: (String) -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Filter options (e.g., All, Price, Category, etc.)
            val filters = listOf("All", "Price", "Category", "Brand")

            filters.forEach { filter ->
                TextButton(
                    onClick = { onFilterChange(filter) },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(
                        text = filter,
                        color = if (selectedFilter == filter) MaterialTheme.colorScheme.primary else Color.Gray,
                        fontSize = 14.sp
                    )
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
    var expanded by remember { mutableStateOf(false) }
    var selectedSort by remember { mutableStateOf("asc") }

    Box( contentAlignment = Alignment.Center) {
        OutlinedButton(
            onClick = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Sort by Price")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            DropdownMenuItem(onClick = {
                selectedSort = "asc"
                productsViewModel.sortByPrice("asc")
                expanded = false
            }, text =  {
                Text("Lowest to Highest")
            })

            DropdownMenuItem(onClick = {
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
    var expanded by remember { mutableStateOf(false) }
    var selectedSort by remember { mutableStateOf(0) }

    Box( contentAlignment = Alignment.Center) {
        OutlinedButton(
            onClick = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Sort by Price")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            DropdownMenuItem(onClick = {
                selectedSort = 1
                productsViewModel.filterByPrice(selectedSort)
                expanded = false
            }, text =  {
                Text("0-2")
            })

            DropdownMenuItem(onClick = {
                selectedSort = 2
                productsViewModel.filterByPrice(selectedSort)
                expanded = false
            }, text = {
                Text("2-6")
            })
            DropdownMenuItem(onClick = {
                selectedSort = 3
                productsViewModel.filterByPrice(selectedSort)
                expanded = false
            }, text = {
                Text("Greater than 6")
            })
        }
    }
}


