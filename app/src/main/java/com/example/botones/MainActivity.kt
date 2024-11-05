package com.example.botones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Cambia MyComposeAppTheme por MaterialTheme
            MaterialTheme {
                // Llamamos a la función principal de UI
                ButtonToggleScreen()
            }
        }
    }
}

@Composable
fun ButtonToggleScreen() {
    var isButtonOneRed by remember { mutableStateOf(true) }
    var isButtonTwoRed by remember { mutableStateOf(false) }
    var showExitDialog by remember { mutableStateOf(false) }
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher


    LaunchedEffect(Unit) {
        while (true) {
            delay(500L)  // Espera 1000ms entre cada número
            isButtonOneRed = !isButtonOneRed


        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(500L)  // Espera 1000ms entre cada número
            isButtonTwoRed = !isButtonTwoRed


        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { isButtonOneRed = !isButtonOneRed },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isButtonOneRed) Color.Red else Color.Green
            ),
            modifier = Modifier
                .padding(8.dp)
                .height(60.dp)
                .width(100.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(text = "1",
                color = Color.DarkGray,
                fontSize = 40.sp)
        }

        Button(
            onClick = { isButtonTwoRed = !isButtonTwoRed },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isButtonTwoRed) Color.Red else Color.Green
            ),
            modifier = Modifier
                .padding(8.dp)
                .width(100.dp)
                .height(60.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(text = "2",
                color = Color.DarkGray,
                fontSize = 40.sp)
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { showExitDialog = true },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray
            ),
        ) { Text(text = "Salir",
                 color = Color.Black,
                 fontSize = 20.sp)}



    }


    if (showExitDialog) {
        ExitConfirmationDialog(
            onConfirm = { onBackPressedDispatcher?.onBackPressed() }, // Llama al onBackPressedDispatcher aquí
            onDismiss = { showExitDialog = false }
        )
    }

}

@Composable
fun ExitConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "¿Está seguro de salir?") },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Sí")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("No")
            }
        }
    )
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    // Cambia MyComposeAppTheme por MaterialTheme
    MaterialTheme {
        ButtonToggleScreen()
    }
}
