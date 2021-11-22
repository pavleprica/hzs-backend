package io.github.com.pavleprica.hzs.todo.service;

import io.github.com.pavleprica.hzs.todo.exceptions.TodoItemNotFound;
import io.github.com.pavleprica.hzs.todo.model.entity.TodoItem;
import io.github.com.pavleprica.hzs.todo.repository.TodoItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Collection;

@SuppressWarnings("ClassCanBeRecord")
@RequiredArgsConstructor
@Service
public class MySqlTodoItemService implements TodoItemService {

    private final TodoItemRepository todoItemRepository;

    @Override
    public TodoItem saveTodoItem(@Valid TodoItem todoItem) {
        todoItem.setId(null);
        return todoItemRepository.save(todoItem);
    }

    @Override
    public TodoItem updateTodoItemById(Long todoItemId, TodoItem todoItem) throws TodoItemNotFound {
        var todoItemOptional = todoItemRepository.findById(todoItemId);

        if (todoItemOptional.isPresent()) {
            var existingTodoItem = todoItemOptional.get();
            todoItem.setId(todoItemId);
            todoItem.setCreatedAt(existingTodoItem.getCreatedAt());
            return todoItemRepository.save(todoItem);
        } else {
            throw new TodoItemNotFound(todoItemId);
        }
    }

    @Override
    public TodoItem getTodoItemById(Long todoItemId) throws TodoItemNotFound {
        var todoItemOptional = todoItemRepository.findById(todoItemId);

        if (todoItemOptional.isPresent()) {
            return todoItemOptional.get();
        } else {
            throw new TodoItemNotFound(todoItemId);
        }
    }

    @Override
    public Collection<TodoItem> getAllTodoItems() {
        return todoItemRepository.findAll();
    }

    @Override
    public void deleteTodoItemById(Long todoItemId) throws TodoItemNotFound {
        if (todoItemRepository.existsById(todoItemId)) {
            todoItemRepository.deleteById(todoItemId);
        } else {
            throw new TodoItemNotFound(todoItemId);
        }
    }

    @Override
    public TodoItem markTodoItemDoneById(Long todoItemId) throws TodoItemNotFound {
        var todoItemOptional = todoItemRepository.findById(todoItemId);

        if (todoItemOptional.isPresent()) {
            var existingTodoItem = todoItemOptional.get();
            existingTodoItem.setIsDone(true);
            return todoItemRepository.save(existingTodoItem);
        } else {
            throw new TodoItemNotFound(todoItemId);
        }
    }

    @Override
    public TodoItem markTodoItemNotDoneById(Long todoItemId) throws TodoItemNotFound {
        var todoItemOptional = todoItemRepository.findById(todoItemId);

        if (todoItemOptional.isPresent()) {
            var existingTodoItem = todoItemOptional.get();
            existingTodoItem.setIsDone(false);
            return todoItemRepository.save(existingTodoItem);
        } else {
            throw new TodoItemNotFound(todoItemId);
        }
    }

}
