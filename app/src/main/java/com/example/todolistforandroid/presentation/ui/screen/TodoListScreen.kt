package com.example.todolistforandroid.presentation.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolistforandroid.presentation.ui.component.TodoItemComponent
import com.example.todolistforandroid.presentation.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    onTodoClick: (com.example.todolistforandroid.domain.model.TodoItem) -> Unit,
    viewModel: TodoViewModel = viewModel()
) {
    val todos by viewModel.todos.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Список задач") },
                modifier = Modifier.semantics { contentDescription = "Список задач" }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            if (todos.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Список задач пуст")
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(todos.size) { index ->
                        TodoItemComponent(
                            todo = todos[index],
                            onToggle = { id -> viewModel.toggleTodo(id) },
                            onClick = onTodoClick
                        )
                    }
                }
            }
        }
    }
}