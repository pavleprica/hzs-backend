package io.github.com.pavleprica.hzs.todo.model.entity;

import io.github.com.pavleprica.hzs.todo.model.TodoItemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

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

}
