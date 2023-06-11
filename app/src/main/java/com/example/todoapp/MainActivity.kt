package com.example.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todoapp.ui.add_edit_todo.AddEditTodoScreen
import com.example.todoapp.ui.todo_list.TodoListScreen
import com.example.mynewapp.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()


            NavHost(navController = navController, startDestination = Routes.TODO_LIST) {
                composable(Routes.TODO_LIST) {
                    TodoListScreen(onNavigate = {
                        navController.navigate(it.route)
                    })
                }

                composable(
                    Routes.ADD_EDIT_TODO + "?todoId={todoId}",
                    arguments = listOf(navArgument(name = "todoId") {
                        type = NavType.IntType
                        defaultValue = -1
                    })
                ) {
                    AddEditTodoScreen(onPopBackStack = {
                        navController.popBackStack()
                    })
                }
            }


        }


    }
}