package com.rawg.games.ui.components.bottomBar

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rawg.games.R
import com.rawg.games.ui.navigation.Route

@Composable
internal fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val bottomBarIconSize = dimensionResource(R.dimen.bottom_bar_icon_size)

    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
        actions = {
            Spacer(modifier = Modifier.weight(1F))
            IconButton(
                onClick = { navController.navigate(Route.Home.route) },
            ) {
                Icon(
                    painter = painterResource(R.drawable.outline_gamepad_24),
                    contentDescription = "Home Screen",
                    modifier = Modifier
                        .size(bottomBarIconSize)
                )
            }
            Spacer(modifier = Modifier.weight(1F))
            IconButton(
                onClick = { navController.navigate(Route.Search.route) }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Discover Screen",
                    modifier = Modifier
                        .size(bottomBarIconSize)
                )
            }
            Spacer(modifier = Modifier.weight(1F))
            IconButton(
                onClick = { navController.navigate(Route.Favorite.route) }
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Discover Screen",
                    modifier = Modifier
                        .size(bottomBarIconSize)
                )
            }
            Spacer(modifier = Modifier.weight(1F))
            IconButton(
                onClick = { navController.navigate(Route.Settings.route) }
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Discover Setting",
                    modifier = Modifier
                        .size(bottomBarIconSize)
                )
            }
            Spacer(modifier = Modifier.weight(1F))
        },
        modifier = modifier
            .fillMaxWidth()
    )
}

@Composable
@Preview
internal fun BottomBarPreview() {
    BottomBar(rememberNavController())
}