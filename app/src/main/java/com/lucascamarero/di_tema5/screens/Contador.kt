package com.lucascamarero.di_tema5.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lucascamarero.di_tema5.viewmodels.UserViewModel

@Composable
fun Contador(userViewModel: UserViewModel) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text("Contador",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.padding(10.dp))

            HorizontalDivider()

            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Column {
                    IconButton(onClick = {
                        userViewModel.setNum(-1)
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Restar",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(200.dp)
                        )
                    }

                    Text("Restar",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium)

                }

                Spacer(modifier = Modifier.padding(horizontal = 30.dp))

                Column {
                    IconButton(onClick = {
                        userViewModel.setNum(1)
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropUp,
                            contentDescription = "Sumar",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(200.dp)
                        )
                    }

                    Text("Sumar",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium)
                }
            }

            Spacer(modifier = Modifier.padding(30.dp))

            Text("El contador es ${userViewModel.pulsaciones}",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge)
        }
    }
}