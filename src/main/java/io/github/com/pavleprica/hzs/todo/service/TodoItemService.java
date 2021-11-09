package io.github.com.pavleprica.hzs.todo.service;

import io.github.com.pavleprica.hzs.todo.model.entity.TodoItem;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface TodoItemService {

    TodoItem saveTodoItem(TodoItem todoItem);

    TodoItem updateTodoItemById(Long todoItemId, TodoItem todoItem);

    Optional<TodoItem> getTodoItemById(Long todoItemId);

    Collection<TodoItem> getAllTodoItems();

    void deleteTodoItemById(Long todoItemId);

}
