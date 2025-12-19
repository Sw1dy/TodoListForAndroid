package com.example.todolistforandroid.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolistforandroid.presentation.ui.screen.TodoDetailScreen
import com.example.todolistforandroid.presentation.ui.screen.TodoListScreen
import com.example.todolistforandroid.presentation.viewmodel.TodoViewModel

sealed class Screen(val route: String) {
    object List : Screen("list")
    object Detail : Screen("detail")
}

@Composable
fun SetupNavigation(navController: NavHostController, viewModel: TodoViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.List.route
    ) {
        composable(Screen.List.route) {
            TodoListScreen(
                onTodoClick = { todo ->
                    viewModel.selectTodo(todo)
                    navController.navigate(Screen.Detail.route)
                },
                viewModel = viewModel
            )
        }
        composable(Screen.Detail.route) {
            TodoDetailScreen(
                onBackClick = {
                    viewModel.clearSelectedTodo()
                    navController.popBackStack()
                },
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun rememberTodoNavController() = rememberNavController()