package io.github.com.pavleprica.hzs.todo.repository;

import io.github.com.pavleprica.hzs.todo.model.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
}
