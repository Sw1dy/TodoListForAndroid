package com.example.todolistforandroid.ui


import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.todolistforandroid.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TodoListScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testAllThreeTodosAreDisplayed() {
        val expectedTodos = listOf(
            "Купить молоко",
            "Позвонить маме",
            "Сделать ДЗ по Android"
        )

        composeTestRule.waitForIdle()

        expectedTodos.forEach { todoTitle ->
            composeTestRule.onNodeWithText(todoTitle).assertIsDisplayed()
        }
    }

    @Test
    fun testCheckboxTogglesStatus() {
        composeTestRule.waitForIdle()

        val todoTitle = "Сделать ДЗ по Android"
        val checkboxContentDesc = "Чекбокс задачи: $todoTitle"

        composeTestRule.onNodeWithText(todoTitle).assertIsDisplayed()

        val checkboxNode = composeTestRule.onNodeWithContentDescription(checkboxContentDesc)
        checkboxNode.assertIsDisplayed()

        checkboxNode.performClick()
        composeTestRule.waitForIdle()
    }

    @Test
    fun testNavigationListToDetailAndBack() {
        composeTestRule.waitForIdle()

        val todoTitle = "Сделать ДЗ по Android"
        composeTestRule.onNodeWithText(todoTitle).performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Детали задачи").assertIsDisplayed()
        composeTestRule.onNodeWithText(todoTitle).assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Назад").performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Список задач").assertIsDisplayed()
        composeTestRule.onNodeWithText(todoTitle).assertIsDisplayed()
    }
}