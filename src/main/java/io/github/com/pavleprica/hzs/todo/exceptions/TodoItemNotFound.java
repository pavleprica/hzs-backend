package io.github.com.pavleprica.hzs.todo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TodoItemNotFound extends Exception {

    public TodoItemNotFound(Long id) {
        super("Todo item with id" + id + " doesn't exist");
    }

}
