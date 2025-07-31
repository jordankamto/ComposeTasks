package com.example.todoapp

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.ui.theme.ToDoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoAppTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TodoScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TodoScreen(modifier: Modifier) {
    var todoText by remember { mutableStateOf("") }
    var todos by remember { mutableStateOf(listOf<String>()) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Blue)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
        ) {
//            TodoInput(value = todoText, onChangeValue = {newText -> todoText = newText})
            OutlinedTextField(
                value = todoText,
                onValueChange = { newText -> todoText = newText },
                label = {
                    Text("Entrer une tache a faire")
                },

                )
            Button(onClick = {
                if (todoText.isNotBlank()) {
                    todos = todos + todoText
                }

            }) { Text(text = "Add") }
        }
        LazyColumn {
            items(todos) { currentTodo ->
                Text(text = currentTodo)
            }
        }
    }
}

//@Composable
//fun TodoInput(value: String, onChangeValue: (String) -> Unit, modifier: Modifier = Modifier) {
//    OutlinedTextField(value = value, onValueChange = onChangeValue, label = {
//        Text("Entrer une tache a faire")
//    }, modifier = Modifier.fillMaxWidth())
//}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToDoAppTheme {
        TodoScreen(modifier = Modifier)
    }
}