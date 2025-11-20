package com.lucascamarero.di_tema5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lucascamarero.di_tema5.screens.BarraProgreso
import com.lucascamarero.di_tema5.screens.Carrusel
import com.lucascamarero.di_tema5.screens.Contador
import com.lucascamarero.di_tema5.screens.CuadroDialogo
import com.lucascamarero.di_tema5.screens.Distintivo
import com.lucascamarero.di_tema5.screens.FormateadorDirecciones
import com.lucascamarero.di_tema5.screens.Galeria
import com.lucascamarero.di_tema5.screens.SelectorColores
import com.lucascamarero.di_tema5.screens.TodoList
import com.lucascamarero.di_tema5.screens.TodoListOrdenacion
import com.lucascamarero.di_tema5.ui.theme.DI_Tema5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DI_Tema5Theme {
                VentanaPrincipal()
            }
        }
    }
}

@Composable
fun VentanaPrincipal() {
    var navController = rememberNavController()
    //val userViewModel: UserViewModel = viewModel()

    Scaffold(
        // barra superior
        //topBar = { BarraSuperior() },

        // cuerpo central
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // definición de rutas de pantallas
            NavHost(
                navController = navController,
                startDestination = "contador"
            ) {
                composable("contador") {
                    Contador()
                }
                composable("barra") {
                    BarraProgreso()
                }
                composable("galeria") {
                    Galeria()
                }
                composable("formateador") {
                    FormateadorDirecciones()
                }
                composable("todo1") {
                    TodoList()
                }
                composable("selector") {
                    SelectorColores()
                }
                composable("todo2") {
                    TodoListOrdenacion()
                }
                composable("distintivo") {
                    Distintivo()
                }
                composable("carrusel") {
                    Carrusel()
                }
                composable("cuadro") {
                    CuadroDialogo()
                }
            }
        }
    }
}
