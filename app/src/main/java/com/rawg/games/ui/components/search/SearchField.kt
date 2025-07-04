package com.rawg.games.ui.components.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchField(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChanged,
        placeholder = { Text("Search game...") },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(onClick = { onSearchQueryChanged("") }) {
                    Icon(Icons.Default.Clear, contentDescription = "Clear Icon")
                }
            }
        },
        singleLine = true,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.fillMaxWidth(),
    )
}

@Composable
@Preview(showBackground = true)
fun SearchFieldPreview() {
    SearchField(
        searchQuery = "",
        onSearchQueryChanged = {},
    )
}
