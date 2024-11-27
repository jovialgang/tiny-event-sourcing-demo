package ru.quipy.api

import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event
import java.util.*

const val TASKE_CREATED_EVENT = "TASK_CREATED_EVENT"
const val TASK_DELETED_EVENT = "TASK_DELETED_EVENT"
const val TASK_UPDATED_EVENT = "TASK_UPDATED_EVENT"

// API
@DomainEvent(name = TASKE_CREATED_EVENT)
class TaskCreatedEvent(
    val taskId: UUID,
    val taskName: String,
    val description: String,
    val deadline: String,
    val projectId: UUID,
    val assigneeId: UUID,
    val creatorId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<TaskAggregate>(
    name = TASKE_CREATED_EVENT,
    createdAt = createdAt
)

@DomainEvent(name = TASK_UPDATED_EVENT)
class TaskUpdatedEvent(
    val taskId: UUID,
    val taskName: String,
    val description: String,
    val deadline: String,
    val projectId: UUID,
    val assigneeId: UUID,
    val creatorId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<TaskAggregate>(
    name = TASK_UPDATED_EVENT,
    createdAt = createdAt
)

@DomainEvent(name = TASK_DELETED_EVENT)
class TaskDeletedEvent(
    val taskId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<TaskAggregate> (
    name = TASK_DELETED_EVENT,
    createdAt = createdAt
)