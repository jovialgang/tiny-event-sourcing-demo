package ru.quipy.projections

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import ru.quipy.api.UserAggregate
import ru.quipy.api.TaskAggregate
import ru.quipy.api.TaskUdpatedEvent
import ru.quipy.api.TaskeCreatedEvent
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
        }
    }
}