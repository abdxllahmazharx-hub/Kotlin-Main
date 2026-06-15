package domain

data class Task(
    val id: Int,
    val title: String,
    val priority: Priority,
    var completed: Boolean = false
) {
    override fun toString(): String {
        return "#$id [$priority] ${if (completed) "DONE" else "PENDING"} - $title"
    }
}