package com.lucascamarero.di_tema5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lucascamarero.di_tema5.screens.BarraProgreso
import com.lucascamarero.di_tema5.screens.Carrusel
import com.lucascamarero.di_tema5.screens.Contador
import com.lucascamarero.di_tema5.screens.CuadroDialogo
import com.lucascamarero.di_tema5.screens.FormateadorDirecciones
import com.lucascamarero.di_tema5.screens.Galeria
import com.lucascamarero.di_tema5.screens.SelectorColores
import com.lucascamarero.di_tema5.screens.TodoList
import com.lucascamarero.di_tema5.screens.TodoListOrdenacion
import com.lucascamarero.di_tema5.ui.theme.DI_Tema5Theme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lucascamarero.di_tema5.viewmodels.ContadorViewModel
import com.lucascamarero.di_tema5.viewmodels.DireccionesViewModel
import com.lucascamarero.di_tema5.viewmodels.GalleryViewModel
import com.lucascamarero.di_tema5.viewmodels.ListaViewModel
import com.lucascamarero.di_tema5.viewmodels.ProgresoViewModel
import kotlinx.coroutines.launch

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VentanaPrincipal() {

    val navController = rememberNavController()
    val contadorViewModel: ContadorViewModel = viewModel()
    val progresoViewModel: ProgresoViewModel = viewModel()
    val direccionesViewModel: DireccionesViewModel = viewModel()
    val listaViewModel: ListaViewModel = viewModel()
    val galleryViewModel: GalleryViewModel = viewModel()

    // Drawer state
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Box(
                modifier = Modifier
                    .padding(top = 160.dp, bottom = 260.dp)
                    .fillMaxHeight()
                    .width(240.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                MenuLateral(navController = navController, drawerState = drawerState)
            }
        }
    ) {
        Scaffold(
            topBar = {
                BarraSuperior(
                    onMenuClick = { scope.launch { drawerState.open() } },
                    contadorViewModel = contadorViewModel
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {

                NavHost(
                    navController = navController,
                    startDestination = "contador"
                ) {
                    composable("contador") { Contador(contadorViewModel) }
                    composable("barra") { BarraProgreso(progresoViewModel) }
                    composable("galeria") { Galeria(galleryViewModel) }
                    composable("formateador") { FormateadorDirecciones(direccionesViewModel) }
                    composable("todo1") { TodoList(listaViewModel) }
                    composable("selector") { SelectorColores() }
                    composable("todo2") { TodoListOrdenacion() }
                    composable("carrusel") { Carrusel() }
                    composable("cuadro") { CuadroDialogo() }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperior(
    onMenuClick: () -> Unit,
    contadorViewModel: ContadorViewModel
) {
    val count by contadorViewModel.count.observeAsState(0)

    TopAppBar(
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(top = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                // Icono del menú desplegable
                IconButton(onClick = onMenuClick) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(30.dp)
                    )
                }

                // DISTINTIVO de pulsaciones sobre un texto
                BadgedBox(
                    badge = {
                        Badge(
                            modifier = Modifier.size(36.dp),
                            containerColor = MaterialTheme.colorScheme.error,
                            contentColor = MaterialTheme.colorScheme.onError
                        ) {
                            Text(
                                text = count.toString(),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                ) {
                    Text(
                        "Contador",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    )
}

@Composable
fun MenuLateral(navController: NavController, drawerState: DrawerState) {

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = 20.dp, vertical = 30.dp)
    ) {

        Text(
            "Menú",
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.padding(20.dp))

        DrawerOpcion("Contador", "contador", navController, drawerState)
        DrawerOpcion("Barra de progreso", "barra", navController, drawerState)
        DrawerOpcion("Galería", "galeria", navController, drawerState)
        DrawerOpcion("Selector de colores", "selector", navController, drawerState)
        DrawerOpcion("Todo List", "todo1", navController, drawerState)
        DrawerOpcion("Todo List Ordenación", "todo2", navController, drawerState)
        DrawerOpcion("Formateador de direcciones", "formateador", navController, drawerState)
        DrawerOpcion("Carrusel", "carrusel", navController, drawerState)
        DrawerOpcion("Cuadro de diálogo", "cuadro", navController, drawerState)
    }
}

@Composable
fun DrawerOpcion(
    nombre: String,
    ruta: String,
    navController: NavController,
    drawerState: DrawerState
) {
    val scope = rememberCoroutineScope()

    Text(
        text = nombre,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(ruta)
                scope.launch { drawerState.close() }
            },
        color = MaterialTheme.colorScheme.onPrimary,
        style = MaterialTheme.typography.bodyMedium
    )
}