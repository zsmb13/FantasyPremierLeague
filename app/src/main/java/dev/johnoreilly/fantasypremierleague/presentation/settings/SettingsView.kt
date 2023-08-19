@file:OptIn(ExperimentalMaterial3Api::class)

package dev.johnoreilly.fantasypremierleague.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.johnoreilly.fantasypremierleague.presentation.FantasyPremierLeagueViewModel

@Composable
fun SettingsView(viewModel: FantasyPremierLeagueViewModel, popBackStack: () -> Unit) {

    val leagueIdsString = remember {
        mutableStateOf<String>(viewModel.leagues.value.joinToString())
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = { popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .padding(16.dp)
        ) {

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = leagueIdsString.value,
                onValueChange = { leagueIds ->
                    leagueIdsString.value = leagueIds
                })
            Button(onClick = {
                viewModel.updateLeagues(leagueIdsString.value.split(", "))
                //viewModel.updateLeagues(listOf("2263", "622004"))
            }) {
                Text("Set Leagues")
            }
        }
    }
}