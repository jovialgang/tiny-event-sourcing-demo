package ru.quipy.controller

import org.hamcrest.StringDescription
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.quipy.api.*
import ru.quipy.core.EventSourcingService
import ru.quipy.logic.ProjectAggregateState
import ru.quipy.logic.TaskAggregateState
import ru.quipy.logic.addTask
import ru.quipy.logic.create
import java.util.*

@RestController
@RequestMapping("/tasks")
class TaskController(
    val taskEsService: EventSourcingService<UUID, TaskAggregate, TaskAggregateState>
) {

    @PostMapping("/{taskName}")
    fun createTask(@PathVariable taskName: String, @RequestParam description: String, @RequestParam deadline: String, @RequestParam projectId: String, @RequestParam assigneeId: String, @RequestParam creatorId: String) : TaskeCreatedEvent {
        return taskEsService.create { it.create(UUID.randomUUID(), taskName, description, deadline, projectId, assigneeId, creatorId) }
        //return projectEsService.create { it.create(UUID.randomUUID(), projectTitle, creatorId) }
    }
//    taskName = name,
//    description = taskDescription,
//    deadline = taskDeadline,
//    projectId = projectId,
//    assigneeId = assigneeId,
//    creatorId = creatorId

    @GetMapping("/{taskId}")
    fun getTask(@PathVariable taskId: UUID) : TaskAggregateState? {
        return taskEsService.getState(taskId)
    }


}