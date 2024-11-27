package ru.quipy.logic

import java.util.*
import ru.quipy.api.*
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState

// Service's business logic
class TaskAggregateState : AggregateState<UUID, TaskAggregate> {
    private lateinit var taskId: UUID
    var createdAt: Long = System.currentTimeMillis()
    var updatedAt: Long = System.currentTimeMillis()

    lateinit var name: String
    lateinit var description: String
    lateinit var deadline: String
    lateinit var projectId: UUID
    lateinit var assigneeId: UUID
    lateinit var creatorId: UUID

    override fun getId() = taskId

    @StateTransitionFunc
    fun taskCreatedApply(event: TaskCreatedEvent) {
        taskId = event.taskId
        name = event.taskName
        description = event.description
        deadline = event.deadline
        projectId = event.projectId
        assigneeId = event.assigneeId
        creatorId = event.creatorId
        updatedAt = createdAt
    }

    @StateTransitionFunc
    fun taskUpdatedApply(event: TaskUpdatedEvent) {
        event.taskName.let { name = it }
        event.description.let { description = it }
        event.deadline.let { deadline = it }
        event.projectId.let { projectId = it }
        event.assigneeId.let { assigneeId = it }
        event.creatorId.let { creatorId = it }
        updatedAt = System.currentTimeMillis()
    }

    @StateTransitionFunc
    fun taskDeletedApply(event: TaskDeletedEvent) {
        taskId = event.taskId
        name = "Deleted"
        description = ""
        deadline = ""
        updatedAt = System.currentTimeMillis()
    }
}
