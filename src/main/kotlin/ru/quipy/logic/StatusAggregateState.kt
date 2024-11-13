package ru.quipy.logic

import ru.quipy.api.*
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import java.util.*

// Service's business logic
class StatusAggregateState : AggregateState<UUID, StatusAggregate> {
    private lateinit var statusId: UUID

    override fun getId() = statusId
}