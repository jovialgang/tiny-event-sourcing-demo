package ru.quipy.logic

import java.util.*
import ru.quipy.api.UserCreatedEvent
import ru.quipy.api.UserUpdatedEvent

fun UserAggregateState.create(id: UUID, username: String, email: String): UserCreatedEvent {
    return UserCreatedEvent(userId = id, username = username, email = email)
}

fun UserAggregateState.update(username: String?, email: String?): UserUpdatedEvent {
    return UserUpdatedEvent(userId = this.getId(), username = username, email = email)
}
