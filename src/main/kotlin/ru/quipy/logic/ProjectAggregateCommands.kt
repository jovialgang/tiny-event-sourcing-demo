package ru.quipy.logic

import java.util.*
import ru.quipy.api.ProjectCreatedEvent

// Commands : takes something -> returns event
// Here the commands are represented by extension functions, but also can be the class member
// functions

fun ProjectAggregateState.create(id: UUID, title: String): ProjectCreatedEvent {
    if (title.length > 100) {
        throw IllegalArgumentException("project title is too big")
    }

    return ProjectCreatedEvent(
            projectId = id,
            title = title,
    )
}
