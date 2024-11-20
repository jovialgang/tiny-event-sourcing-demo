package ru.quipy.logic

import ru.quipy.api.*
import java.util.*


// Commands : takes something -> returns event
// Here the commands are represented by extension functions, but also can be the class member functions

fun TaskAggregateState.create(id: UUID, name: String, taskDescription: String, taskDeadline: String, projectId: String, assigneeId: String, creatorId: String): TaskeCreatedEvent {
    return TaskeCreatedEvent(
        taskId = id,
        taskName = name,
        description = taskDescription,
        deadline = taskDeadline,
        projectId = projectId,
        assigneeId = assigneeId,
        creatorId = creatorId
    )
}

fun TaskAggregateState.update(name: String, taskDescription: String, taskDeadline: String, projectId: String, assigneeId: String, creatorId: String): TaskUdpatedEvent {
    return TaskUdpatedEvent(
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
    return TaskDeletedEvent(
        taskId = id
    )
}