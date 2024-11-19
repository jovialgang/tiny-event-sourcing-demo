package ru.quipy.projections

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import ru.quipy.api.*
import ru.quipy.streams.AggregateSubscriptionsManager
import javax.annotation.PostConstruct

class TaskEventsSubscriber {
    val logger: Logger = LoggerFactory.getLogger(TaskEventsSubscriber::class.java)

    @Autowired
    lateinit var subscriptionsManager: AggregateSubscriptionsManager

    @PostConstruct
    fun init() {
        subscriptionsManager.createSubscriber(TaskAggregate::class, "task-events-subscriber") {

            `when`(TaskeCreatedEvent::class) { event ->
                logger.info("Task created: {}", event.taskName)
            }

            `when`(TaskUdpatedEvent::class) { event ->
                logger.info("Task updated. {}", event.taskName)
            }

            `when`(TaskDeletedEvent::class) { event ->
                logger.info("Task deleted. {}", event.taskId)
            }
        }
    }
}