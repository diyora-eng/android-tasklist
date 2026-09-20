package com.xemoado.tasklist
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = TaskDatabase.getInstance(applicationContext).taskDao()

        setContent {
            MaterialTheme {
                TaskScreen(dao)
            }
        }
    }
}

@Composable
fun TaskScreen(dao: TaskDao) {

    val tasks by dao.getAll().collectAsState(initial = emptyList())

    var input by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                label = { Text("Новая задача") },
                modifier = Modifier.weight(1f)
            )

            Spacer(Modifier.width(8.dp))

            Button(onClick = {
                val text = input.trim()

                if (text.isNotBlank()) {
                    scope.launch {
                        dao.insert(Task(text = text))
                    }
                    input = ""
                }
            }) {
                Text("+")
            }
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(tasks, key = { it.id }) { task ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Text(
                        text = task.text,
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(onClick = {
                        scope.launch { dao.delete(task) }
                    }) {
                        Text("✕")
                    }
                }
            }
        }
    }
}
