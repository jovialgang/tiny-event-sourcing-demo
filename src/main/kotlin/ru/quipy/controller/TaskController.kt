package ru.quipy.controller

import org.hamcrest.StringDescription
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.quipy.api.*
import ru.quipy.core.EventSourcingService
import ru.quipy.logic.*
import java.util.*

@RestController
@RequestMapping("/tasks")
class TaskController(
    val taskEsService: EventSourcingService<UUID, TaskAggregate, TaskAggregateState>
) {

    @PostMapping("/{taskName}")
    fun createTask(@PathVariable taskName: String,
                   @RequestParam description: String,
                   @RequestParam deadline: String,
                   @RequestParam projectId: String,
                   @RequestParam assigneeId: String,
                   @RequestParam creatorId: String) : TaskeCreatedEvent {
        return taskEsService.create { it.create(UUID.randomUUID(), taskName, description, deadline, projectId, assigneeId, creatorId) }
    }

    @GetMapping("/{taskId}")
    fun getTask(@PathVariable taskId: UUID) : TaskAggregateState? {
        return taskEsService.getState(taskId)
    }

    @PostMapping("/{taskId}")
    fun updateTask(@PathVariable taskId: UUID,
                   @RequestParam taskName: String,
                   @RequestParam description: String,
                   @RequestParam deadline: String,
                   @RequestParam projectId: String,
                   @RequestParam assigneeId: String,
                   @RequestParam creatorId: String) : TaskUdpateEvent {
        return taskEsService.update(taskId) { it.update(taskName, description, deadline, projectId, assigneeId, creatorId) }
    }

    @DeleteMapping("/{taskId}")
    fun deleteTask(@PathVariable taskId: UUID) : TaskDeletedEvent{
        return taskEsService.update(taskId) { it.delete(taskId) }
    }


}