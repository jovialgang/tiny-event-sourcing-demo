package ru.quipy.api

import java.util.*
import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event

const val PROJECT_CREATED_EVENT = "PROJECT_CREATED_EVENT"

@DomainEvent(name = PROJECT_CREATED_EVENT)
class ProjectCreatedEvent(
        val projectId: UUID,
        val title: String,
        createdAt: Long = System.currentTimeMillis(),
) :
        Event<ProjectAggregate>(
                name = PROJECT_CREATED_EVENT,
                createdAt = createdAt,
        )
