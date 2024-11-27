package ru.quipy.logic

import java.util.*
import ru.quipy.api.*

// Commands : takes something -> returns event
// Here the commands are represented by extension functions, but also can be the class member
// functions

fun TaskAggregateState.create(
        id: UUID,
        name: String,
        taskDescription: String,
        taskDeadline: String,
        projectId: UUID,
        assigneeId: UUID,
        creatorId: UUID
): TaskCreatedEvent {
        if (name.length > 100) {
                throw IllegalArgumentException("name is too big")
        }

        if (taskDescription.length > 500) {
                throw IllegalArgumentException("description is too big")
        }

        if (taskDescription.length > 500) {
                throw IllegalArgumentException("description is too big")
        }

        return TaskCreatedEvent(
                taskId = id,
                taskName = name,
                description = taskDescription,
                deadline = taskDeadline,
                projectId = projectId,
                assigneeId = assigneeId,
                creatorId = creatorId
        )
}

fun TaskAggregateState.update(
        name: String,
        taskDescription: String,
        taskDeadline: String,
        projectId: UUID,
        assigneeId: UUID,
        creatorId: UUID
): TaskUpdatedEvent {
        if (name.length > 100) {
                throw IllegalArgumentException("name is too big")
        }

        if (taskDescription.length > 500) {
                throw IllegalArgumentException("description is too big")
        }

        if (taskDescription.length > 500) {
                throw IllegalArgumentException("description is too big")
        }

        return TaskUpdatedEvent(
                taskId = this.getId(),
                taskName = name,
                description = taskDescription,
                deadline = taskDeadline,
                projectId = projectId,
                assigneeId = assigneeId,
                creatorId = creatorId
        )
}

fun TaskAggregateState.delete(id: UUID): TaskDeletedEvent {
        return TaskDeletedEvent(taskId = id)
}
