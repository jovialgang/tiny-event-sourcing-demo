package ru.quipy.api

import java.util.*
import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event

const val STATUS_CREATED_EVENT = "STATUS_CREATED_EVENT"

@DomainEvent(name = STATUS_CREATED_EVENT)
class StatusCreatedEvent(
        val statusId: UUID,
        val title: String,
        createdAt: Long = System.currentTimeMillis(),
) :
        Event<StatusAggregate>(
                name = STATUS_CREATED_EVENT,
                createdAt = createdAt,
        )
