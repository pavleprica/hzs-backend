package io.github.com.pavleprica.hzs.todo.service;

import io.github.com.pavleprica.hzs.todo.exceptions.TodoItemNotFound;
import io.github.com.pavleprica.hzs.todo.model.entity.TodoItem;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface TodoItemService {

    TodoItem saveTodoItem(TodoItem todoItem);

    TodoItem updateTodoItemById(Long todoItemId, TodoItem todoItem) throws TodoItemNotFound;

    TodoItem getTodoItemById(Long todoItemId) throws TodoItemNotFound;

    Collection<TodoItem> getAllTodoItems();

    void deleteTodoItemById(Long todoItemId) throws TodoItemNotFound;

    TodoItem markTodoItemDoneById(Long todoItemId) throws TodoItemNotFound;

    TodoItem markTodoItemNotDoneById(Long todoItemId) throws TodoItemNotFound;

}
