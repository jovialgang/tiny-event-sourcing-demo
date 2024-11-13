package ru.quipy.logic

import ru.quipy.api.*
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import java.util.*

// Service's business logic
class UserAggregateState : AggregateState<UUID, UserAggregate> {
    private lateinit var userId: UUID

    override fun getId() = userId
}