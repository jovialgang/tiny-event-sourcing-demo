package ru.quipy.logic

import java.util.*
import ru.quipy.api.*
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState

// Service's business logic
class StatusAggregateState : AggregateState<UUID, StatusAggregate> {
    private lateinit var statusId: UUID

    lateinit var title: String

    override fun getId() = statusId

    @StateTransitionFunc
    fun statusCreatedApply(event: StatusCreatedEvent) {
        statusId = event.statusId
        title = event.title
    }
}
