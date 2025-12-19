package com.example.todolistforandroid.domain.usecase


import com.example.todolistforandroid.domain.model.TodoItem
import com.example.todolistforandroid.domain.repository.TodoRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetTodosUseCaseTest {

    private lateinit var repository: TodoRepository
    private lateinit var getTodosUseCase: GetTodosUseCase

    private val testTodos = listOf(
        TodoItem(1, "Купить молоко", "2 литра, обезжиренное", false),
        TodoItem(2, "Позвонить маме", "Спросить про выходные", true),
        TodoItem(3, "Сделать ДЗ по Android", "Clean Architecture + Compose", false)
    )

    @Before
    fun setUp() {
        repository = mockk()
        getTodosUseCase = GetTodosUseCase(repository)
    }

    @Test
    fun `when invoke called should return 3 todos`() = runTest {
        coEvery { repository.getTodos() } returns testTodos

        val result = getTodosUseCase()

        assertEquals(3, result.size)
        assertEquals(testTodos, result)
    }
}