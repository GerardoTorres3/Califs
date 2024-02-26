package com.example.califs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun App() {
    var calificacion1 by remember { mutableStateOf("") }
    var calificacion2 by remember { mutableStateOf("") }
    var calificacion3 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<String?>(null) }

    Column {
        TextField(
            value = calificacion1,
            onValueChange = { calificacion1 = it },
            label = { Text("Calificación 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = calificacion2,
            onValueChange = { calificacion2 = it },
            label = { Text("Calificación 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = calificacion3,
            onValueChange = { calificacion3 = it },
            label = { Text("Calificación 3") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(onClick = {
            val promedio = calcularPromedio(calificacion1, calificacion2, calificacion3)
            resultado = generarMensaje(promedio)
        }) {
            Text("Calcular Promedio")
        }
        resultado?.let {
            Text(it)
        }
    }
}

fun calcularPromedio(calificacion1: String, calificacion2: String, calificacion3: String): Double {
    return (calificacion1.toDouble() + calificacion2.toDouble() + calificacion3.toDouble()) / 3
}

fun generarMensaje(promedio: Double): String {
    return when {
        promedio < 7 -> "El alumno repetirá el semestre"
        promedio < 8.5 -> "Has perdido 5% de beca"
        else -> "Felicidades eres un estudiante de honor"
    }
}
