package io.github.com.pavleprica.hzs.todo.repository;

import io.github.com.pavleprica.hzs.todo.model.entity.ScheduledEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledEventRepository extends JpaRepository<ScheduledEvent, Long> {
}
