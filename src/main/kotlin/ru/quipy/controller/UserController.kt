package ru.quipy.controller

import org.springframework.web.bind.annotation.*
import ru.quipy.api.UserAggregate
import ru.quipy.api.UserCreatedEvent
import ru.quipy.api.UserUpdatedEvent
import ru.quipy.core.EventSourcingService
import ru.quipy.logic.UserAggregateState
import ru.quipy.logic.create
import ru.quipy.logic.update
import java.util.*

@RestController
@RequestMapping("/users")
class UserController(
    val userEsService: EventSourcingService<UUID, UserAggregate, UserAggregateState>
) {
    @PostMapping
    fun createUser(@RequestParam username: String, @RequestParam email: String): UserCreatedEvent {
        return userEsService.create { it.create(UUID.randomUUID(), username, email) }
    }

    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: UUID): UserAggregateState? {
        return userEsService.getState(userId)
    }

    @PutMapping("/{userId}")
    fun updateUser(
        @PathVariable userId: UUID,
        @RequestParam(required = false) username: String?,
        @RequestParam(required = false) email: String?
    ): UserUpdatedEvent {
        return userEsService.update(userId) { it.update(username, email) }
    }
}