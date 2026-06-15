package service

import data.FileTaskRepository
import domain.Task
import domain.Priority

class TaskService(private val repo: FileTaskRepository) {

    private val tasks = repo.load()
    private var nextId = (tasks.maxOfOrNull { it.id } ?: 0) + 1

    fun addTask(title: String, priority: Priority) {
        val task = Task(nextId++, title, priority)
        tasks.add(task)
        repo.save(tasks)
    }

    fun listTasks(): List<Task> = tasks

    fun completeTask(id: Int) {
        tasks.find { it.id == id }?.completed = true
        repo.save(tasks)
    }

    fun deleteTask(id: Int) {
        tasks.removeIf { it.id == id }
        repo.save(tasks)
    }
}