package io.github.com.pavleprica.hzs.todo.controller;

import io.github.com.pavleprica.hzs.todo.exceptions.TodoItemNotFound;
import io.github.com.pavleprica.hzs.todo.model.dto.TodoItemDto;
import io.github.com.pavleprica.hzs.todo.model.entity.TodoItem;
import io.github.com.pavleprica.hzs.todo.service.TodoItemService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo-items")
@Api(tags = {"Todo items"})
@Slf4j
public class TodoItemController {

    private final TodoItemService todoItemService;

    @RequestMapping(method = RequestMethod.OPTIONS)
    ResponseEntity<?> options() {
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE)
                .build();
    }

    @GetMapping
    Collection<TodoItemDto> getAllTodoItems() {
        log.info("Getting all todo items");
        return todoItemService
                .getAllTodoItems()
                .stream()
                .map(this::mapToTodoItemDto)
                .toList();
    }

    @GetMapping("/{id}")
    TodoItemDto getTodoItemById(@PathVariable Long id) throws TodoItemNotFound {
        log.info("Get todo item by id [" + id + "]");
        return mapToTodoItemDto(todoItemService.getTodoItemById(id));
    }

    @PostMapping
    ResponseEntity<TodoItemDto> createNewTodoItem(@RequestBody @Valid TodoItemDto todoItemDto) {
        log.info("Save new todo item");
        log.info(String.valueOf(todoItemDto));
        var savedTodoItem = todoItemService.saveTodoItem(mapToTodoItem(todoItemDto));

        return ResponseEntity
                .created(createLocationUri(savedTodoItem.getId()))
                .body(mapToTodoItemDto(savedTodoItem));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTodoItemById(@PathVariable Long id) throws TodoItemNotFound {
        log.info("Deleting todo item [" + id + "]");
        todoItemService.deleteTodoItemById(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<TodoItemDto> updateTodoItemById(@PathVariable Long id, @RequestBody @Valid TodoItemDto todoItemDto) throws TodoItemNotFound {
        var updatedTodoItem = todoItemService.updateTodoItemById(id, mapToTodoItem(todoItemDto));

        return ResponseEntity.created(createSelfLink()).body(mapToTodoItemDto(updatedTodoItem));
    }

    private URI createLocationUri(Long todoItemId) {
        return MvcUriComponentsBuilder
                .fromController(getClass())
                .path("/{id}")
                .buildAndExpand(todoItemId)
                .toUri();
    }

    private URI createSelfLink() {
        return URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
    }

    private TodoItemDto mapToTodoItemDto(TodoItem todoItem) {
        return new TodoItemDto(
                todoItem.getId(),
                todoItem.getTitle(),
                todoItem.getDescription(),
                todoItem.getTodoItemType(),
                todoItem.getCreatedAt()
        );
    }

    private TodoItem mapToTodoItem(TodoItemDto todoItemDto) {
        return new TodoItem(
                todoItemDto.id(),
                todoItemDto.title(),
                todoItemDto.description(),
                todoItemDto.createdAt(),
                todoItemDto.todoItemType(),
                false,
                Collections.emptyList()
        );
    }

}
