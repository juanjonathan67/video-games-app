package com.rawg.games.ui.components.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.rawg.games.ui.components.filter.ordering.OrderingDropdownMenu

@Composable
fun FilterDialog(
    onDismissRequest: () -> Unit,
    onConfirmClicked: (Filter) -> Unit,
    modifier: Modifier = Modifier,
) {
    var filter by remember { mutableStateOf(Filter()) }

    FilterDialogContent(
        filter = filter,
        onFilterChanged = { filter = it },
        onDismissRequest = onDismissRequest,
        onConfirmClicked = { onConfirmClicked(filter) },
        modifier = modifier,
    )
}

@Composable
internal fun FilterDialogContent(
    filter: Filter,
    onFilterChanged: (Filter) -> Unit,
    onDismissRequest: () -> Unit,
    onConfirmClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(modifier = modifier) {
            Column {
                OrderingDropdownMenu(
                    ordering = filter.ordering,
                    onOrderingChanged = { onFilterChanged(filter.copy(ordering = it)) },
                    modifier = Modifier.fillMaxWidth()
                        .padding(8.dp)
                )

                DialogActionButton(
                    onDismissRequest = onDismissRequest,
                    onConfirmClicked = onConfirmClicked,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
internal fun DialogActionButton(
    onDismissRequest: () -> Unit,
    onConfirmClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = modifier.fillMaxWidth()
    ) {
        TextButton(onClick = onDismissRequest) {
            Text("Cancel")
        }
        Spacer(modifier.weight(1F))
        TextButton(onClick = onConfirmClicked) {
            Text("Confirm")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FilterDialogPreview() {
    FilterDialog(
        onDismissRequest = {},
        onConfirmClicked = {},
    )
}
