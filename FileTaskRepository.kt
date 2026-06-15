package app

import service.TaskService
import data.FileTaskRepository
import domain.Priority
import domain.Task

fun main() {
    val repo = FileTaskRepository("tasks.json")
    val service = TaskService(repo)

    println("=== Team Lead Task Manager ===")

    while (true) {
        println("""
        1. Add Task
        2. List Tasks
        3. Complete Task
        4. Delete Task
        5. Exit
        """.trimIndent())

        print("Choose: ")
        when (readLine()?.trim()) {
            "1" -> {
                print("Title: ")
                val title = readLine() ?: ""
                print("Priority (LOW, MEDIUM, HIGH): ")
                val priority = try {
                    Priority.valueOf(readLine()?.trim()?.uppercase() ?: "LOW")
                } catch (e: Exception) {
                    Priority.LOW
                }
                service.addTask(title, priority)
            }
            "2" -> service.listTasks().forEach { println(it) }
            "3" -> {
                print("Task ID: ")
                val id = readLine()?.toIntOrNull() ?: return@when
                service.completeTask(id)
            }
            "4" -> {
                print("Task ID: ")
                val id = readLine()?.toIntOrNull() ?: return@when
                service.deleteTask(id)
            }
            "5" -> return
        }
    }
}