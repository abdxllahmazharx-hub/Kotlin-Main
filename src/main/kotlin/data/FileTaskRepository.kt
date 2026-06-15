package data

import domain.Task
import java.io.File

class FileTaskRepository(private val fileName: String) {

    private val file = File(fileName)

    fun load(): MutableList<Task> {
        if (!file.exists()) return mutableListOf()
        return file.readLines().mapNotNull {
            val parts = it.split("|")
            if (parts.size < 4) return@mapNotNull null
            Task(
                id = parts[0].toInt(),
                title = parts[1],
                priority = enumValueOf(parts[2]),
                completed = parts[3].toBoolean()
            )
        }.toMutableList()
    }

    fun save(tasks: List<Task>) {
        file.writeText(
            tasks.joinToString("\n") {
                "${it.id}|${it.title}|${it.priority}|${it.completed}"
            }
        )
    }
}