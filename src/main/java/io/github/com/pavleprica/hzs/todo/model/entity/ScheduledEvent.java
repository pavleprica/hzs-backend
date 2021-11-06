package io.github.com.pavleprica.hzs.todo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;

/*
Scheduled to a TODO
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ScheduledEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Instant createdAt;

    @ManyToOne
    private TodoItem todoItem;

    private String message;

    private Instant scheduleTime;

}
