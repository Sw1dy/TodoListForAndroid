package com.example.todolistforandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolistforandroid.data.repository.TodoRepositoryImpl
import com.example.todolistforandroid.domain.usecase.GetTodosUseCase
import com.example.todolistforandroid.domain.usecase.ToggleTodoUseCase
import com.example.todolistforandroid.navigation.SetupNavigation
import com.example.todolistforandroid.navigation.rememberTodoNavController
import com.example.todolistforandroid.presentation.viewmodel.TodoViewModel
import com.example.todolistforandroid.ui.theme.ToDoListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val repository = TodoRepositoryImpl(this)
            val viewModel: TodoViewModel = viewModel {
                TodoViewModel(
                    GetTodosUseCase(repository),
                    ToggleTodoUseCase(repository)
                )
            }
            val navController = rememberTodoNavController()

            ToDoListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupNavigation(navController, viewModel)
                }
            }
        }
    }
}