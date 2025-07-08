package com.rawg.games.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.rawg.games.R
import com.rawg.games.domain.model.GameData
import com.rawg.games.ui.components.error.Error
import com.rawg.games.ui.components.filter.FilterDialog
import com.rawg.games.ui.components.game.GameItem
import com.rawg.games.ui.components.loading.Loading
import com.rawg.games.ui.components.search.SearchField
import com.rawg.games.utils.LocalViewModelFactory

@Composable
fun HomeScreen(
    navigateToDetail: (Int) -> Unit,
    homeViewModel: HomeViewModel = viewModel(factory = LocalViewModelFactory.current)
) {
    val bestGames = remember { homeViewModel.games }.collectAsLazyPagingItems()
    var searchQuery by remember { mutableStateOf("") }

    var showFilterDialog by remember { mutableStateOf(false) }

    Column {
        Row {
            SearchField(
                searchQuery = searchQuery,
                onSearchQueryChanged = {
                    searchQuery = it
                    homeViewModel.setSearchQuery(it)
                },
                modifier = Modifier
                    .weight(1F)
                    .padding(4.dp)
            )

            FilterButton(
                onFilterButtonClicked = { showFilterDialog = true },
                modifier = Modifier
                    .padding(4.dp)
            )
        }

        HomeScreenContent(bestGames, navigateToDetail)
    }

    if (showFilterDialog) {
        FilterDialog(
            onDismissRequest = { showFilterDialog = false },
            onConfirmClicked = {
                homeViewModel.setFilter(it)
                showFilterDialog = false
            }
        )
    }
}

@Composable
fun FilterButton(
    onFilterButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {

    IconButton(
        onClick = onFilterButtonClicked,
        modifier = modifier
    ) {
        Icon(
            painterResource(R.drawable.outline_filter_list_24),
            contentDescription = "Filter Button"
        )
    }
}

@Composable
fun HomeScreenContent(
    bestGames: LazyPagingItems<GameData>,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (bestGames.loadState.refresh) {
        is LoadState.Loading -> Loading(modifier)
        is LoadState.Error -> Error(modifier)
        else -> {
            if(bestGames.itemCount == 0) {
                Loading(modifier)
            } else {
                GamesList(bestGames, navigateToDetail, modifier)
            }
        }
    }
}

@Composable
fun GamesList(
    bestGames: LazyPagingItems<GameData>,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier,
    ) {
        items(
            count = bestGames.itemCount,
            key = { bestGames[it]?.id ?: it },
        ) {
            val item = bestGames[it] ?: return@items

            GameItem(
                gameData = item,
                onClick = navigateToDetail
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FilterButtonPreview() {
    FilterButton(
        onFilterButtonClicked = {}
    )
}