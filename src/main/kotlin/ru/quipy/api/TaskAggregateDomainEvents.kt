package ru.quipy.api

import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event
import java.util.*

const val TASKE_CREATED_EVENT = "TASKE_CREATED_EVENT"
//const val TASK_DELETE_EVENT = "TASK_DELETE_EVENT"
const val TASK_UPDATE_EVENT = "TASK_UPDATE_EVENT"

// API
@DomainEvent(name = TASKE_CREATED_EVENT)
class TaskeCreatedEvent(
    val taskId: UUID,
    val taskName: String,
    val description: String,
    val deadline: String,
    val projectId: String,
    val assigneeId: String,
    val creatorId: String,
    createdAt: Long = System.currentTimeMillis(),
) : Event<TaskAggregate>(
    name = TASKE_CREATED_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = TASK_UPDATE_EVENT)
class TaskUdpatedEvent(
    val taskId: UUID,
    val taskName: String,
    val description: String,
    val deadline: String,
    val projectId: String,
    val assigneeId: String,
    val creatorId: String,
    createdAt: Long = System.currentTimeMillis(),
) : Event<TaskAggregate>(
    name = TASK_UPDATE_EVENT,
    createdAt = createdAt,
)