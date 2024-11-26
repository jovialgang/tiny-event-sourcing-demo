package ru.quipy.logic

import java.util.*
import ru.quipy.api.StatusCreatedEvent

// Commands : takes something -> returns event
// Here the commands are represented by extension functions, but also can be the class member
// functions

fun StatusAggregateState.create(id: UUID, title: String): StatusCreatedEvent {
    return StatusCreatedEvent(
            statusId = id,
            title = title,
    )
}
