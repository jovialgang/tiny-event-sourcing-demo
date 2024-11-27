package ru.quipy.projections

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.quipy.api.TaskAggregate
import ru.quipy.api.ProjectAggregate
import ru.quipy.api.UserAggregate
import ru.quipy.api.TaskCreatedEvent
import ru.quipy.api.TaskUpdatedEvent
import ru.quipy.logic.ProjectAggregateState
import ru.quipy.logic.UserAggregateState
import ru.quipy.streams.AggregateSubscriptionsManager
import ru.quipy.core.EventSourcingService
import javax.annotation.PostConstruct
import java.util.UUID

@Service
class TaskEventsSubscriber (
    val projectEsService: EventSourcingService<UUID, ProjectAggregate, ProjectAggregateState>
    val userEsService: EventSourcingService<UUID, UserAggregate, UserAggregateState>
) {

    val logger: Logger = LoggerFactory.getLogger(TaskEventsSubscriber::class.java)

    @Autowired
    lateinit var subscriptionsManager: AggregateSubscriptionsManager

    @PostConstruct
    fun init() {
        subscriptionsManager.createSubscriber(TaskAggregate::class, "project task subscriber") {

            `when`(TaskCreatedEvent::class) { event ->
                if (projectEsService.getState(event.projectId) is null) {
                    logger.info("this project doesnt exist")
                }
                if (userEsService.getState(event.assigneeId) is null) {
                    logger.info("this assignee doesnt exist")
                }
                if (userEsService.getState(event.creatorId) is null) {
                    logger.info("this creator doesnt exist")
                }

                logger.info("Task created: {}", event.taskName)
            }

            `when`(TaskUpdatedEvent::class) { event ->
                if (projectEsService.getState(event.projectId) is null) {
                    logger.info("this project doesnt exist")
                }
                if (userEsService.getState(event.assigneeId) is null) {
                    logger.info("this assignee doesnt exist")
                }
                if (userEsService.getState(event.creatorId) is null) {
                    logger.info("this creator doesnt exist")
                }

                logger.info("Task updated: {}", event.taskName)
            }
        }
    }
}