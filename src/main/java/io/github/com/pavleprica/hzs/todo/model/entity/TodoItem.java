package io.github.com.pavleprica.hzs.todo.model.entity;

import io.github.com.pavleprica.hzs.todo.model.TodoItemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @CreationTimestamp
    private Instant createdAt;

    private TodoItemType todoItemType;

    private boolean finished;

    @OneToMany
    private Collection<ScheduledEvent> scheduledEvents;

}
