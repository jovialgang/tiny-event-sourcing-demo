package ru.quipy.logic

import ru.quipy.api.*
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import java.util.*

// Service's business logic
class TaskAggregateState : AggregateState<UUID, TaskAggregate> {
    private lateinit var taskId: UUID
    var createdAt: Long = System.currentTimeMillis()
    var updatedAt: Long = System.currentTimeMillis()

    lateinit var name: String
    lateinit var description: String
    lateinit var deadline: String
    lateinit var projectId: String
    lateinit var assigneeId: String
    lateinit var creatorId: String
    //var projectTags = mutableMapOf<UUID, TagEntity>()

    override fun getId() = taskId


    @StateTransitionFunc
    fun taskCreatedApply(event: TaskeCreatedEvent) {
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
    fun taskUpdatedApply(event: TaskUdpatedEvent) {
        event.taskName?.let { name = it }
        event.description?.let { description = it }
        event.deadline?.let { deadline = it }
        event.projectId?.let { projectId = it }
        event.assigneeId?.let { assigneeId = it }
        event.creatorId?.let { creatorId = it }
        updatedAt = System.currentTimeMillis()
    }
}
