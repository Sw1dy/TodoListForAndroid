package com.example.todolistforandroid.data.repository


import com.example.todolist.data.local.TodoJsonDataSource
import com.example.todolist.domain.model.TodoItem
import com.example.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoRepositoryImpl(private val context: android.content.Context) : TodoRepository {
    private val jsonDataSource = TodoJsonDataSource(context)
    private var todos = mutableListOf<TodoItem>()

    override suspend fun getTodos(): List<TodoItem> = withContext(Dispatchers.IO) {
        if (todos.isEmpty()) {
            val dtos = jsonDataSource.getTodos()
            todos = dtos.map { dto ->
                TodoItem(
                    id = dto.id,
                    title = dto.title,
                    description = dto.description,
                    isCompleted = dto.isCompleted
                )
            }.toMutableList()
        }
        todos.toList()
    }

    override suspend fun toggleTodo(id: Int) {
        val index = todos.indexOfFirst { it.id == id }
        if (index != -1) {
            todos[index] = todos[index].copy(isCompleted = !todos[index].isCompleted)
        }
    }
}