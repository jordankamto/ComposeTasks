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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
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
                    Column(Modifier.padding(innerPadding)) {
                        TodoScreen()
                    }

                }
            }
        }
    }
}

@Composable
fun TodoScreen() {
    var todoText by remember { mutableStateOf("") }
    var todos by remember { mutableStateOf(listOf<String>()) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()


        ) {
//            TodoInput(value = todoText, onChangeValue = {newText -> todoText = newText})
            OutlinedTextField(
                value = todoText,
                onValueChange = { newText -> todoText = newText },
                label = {
                    Text("Entrer une tache a faire")
                },
                modifier = Modifier.weight(1f)
            )
Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = {
                if (todoText.isNotBlank()) {
                    todos = todos + todoText
                    todoText = ""
                }
            }, shape = RoundedCornerShape(5.dp)) { Text(text = "Ajouter") }
        }
        TodoList(todos = todos)
    }
}

@Composable
fun TodoList(todos: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier) {
        items(todos) { currentTodo ->
            Text(text = currentTodo, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp))
            HorizontalDivider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToDoAppTheme {
        TodoScreen()
    }
}