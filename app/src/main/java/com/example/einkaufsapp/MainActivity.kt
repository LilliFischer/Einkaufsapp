package com.example.einkaufsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.einkaufsapp.ui.theme.EinkaufsappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EinkaufsappTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Einkaufsliste()
                }
            }
        }
    }
}

@Composable
fun Einkaufsliste() {
    //Zustand für die Liste der Artikel
    var einkaufsliste by remember { mutableStateOf(listOf<String>()) }

    //Zustand für das Eingabefeld
    var eingabeText by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = eingabeText,
            onValueChange = { eingabeText = it },
            label = { Text("Neuer Artikel") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val neuerArtikel = eingabeText.text.trim()
                if (neuerArtikel.isNotEmpty()) {
                    einkaufsliste = einkaufsliste + neuerArtikel
                    eingabeText = TextFieldValue("") //Eingabe zurücksetzen
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Hinzufügen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Einkaufsliste:", style = MaterialTheme.typography.titleMedium)

        LazyColumn {
            items(einkaufsliste) { artikel ->
                Text(
                    text = artikel,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            einkaufsliste = einkaufsliste - artikel // Entfernen beim Klicken
                        }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun EinkaufslistePreview() {
    EinkaufsappTheme {
        Einkaufsliste()
    }
}
