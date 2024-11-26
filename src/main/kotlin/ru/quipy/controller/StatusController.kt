package ru.quipy.controller

import java.util.*
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.quipy.api.*
import ru.quipy.core.EventSourcingService
import ru.quipy.logic.*
import ru.quipy.logic.create

@RestController
@RequestMapping("/statuses")
class StatusController(
        val statusEsService: EventSourcingService<UUID, StatusAggregate, StatusAggregateState>
) {

    @PostMapping("/{statusTitle}")
    fun creatStatus(@PathVariable statusTitle: String): StatusCreatedEvent {
        return statusEsService.create { it.create(UUID.randomUUID(), statusTitle) }
    }
}
